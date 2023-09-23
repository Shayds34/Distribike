package com.distribike.features.subfeatures.form.scanner.main

import android.Manifest
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.distribike.databinding.ActivityCameraScannerBinding
import com.distribike.features.subfeatures.form.scanner.main.cameraviews.CameraGraphicOverlay
import com.distribike.features.subfeatures.form.scanner.main.cameraviews.CameraLoadingGraphic
import com.distribike.features.subfeatures.form.scanner.main.cameraviews.CameraReticuleAnimator
import com.distribike.features.subfeatures.form.scanner.main.cameraviews.CameraReticuleGraphic
import com.distribike.features.subfeatures.form.scanner.main.viewmodel.CameraViewModel
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min

@AndroidEntryPoint
class CameraActivity : AppCompatActivity() {

    companion object {
        private const val LOADING_ANIMATION_DURATION = 750L
        const val DESIRED_WIDTH_CROP_PERCENT = 15
        const val DESIRED_HEIGHT_CROP_PERCENT = 90

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

        fun newInstance(context: Context) = Intent(context, CameraActivity::class.java)
    }

    private var _binding: ActivityCameraScannerBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private var cameraControl: CameraControl? = null
    private lateinit var cameraExecutor: ExecutorService

    private lateinit var previewView: PreviewView
    private lateinit var graphicOverlay: CameraGraphicOverlay

    private lateinit var cameraReticuleAnimator: CameraReticuleAnimator
    private lateinit var loadingAnimator: ValueAnimator
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    private val viewModel: CameraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCameraScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        previewView = binding.previewView
        graphicOverlay = binding.overlay

        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels

        cameraExecutor = Executors.newSingleThreadExecutor()

        if (allPermissionsGranted()) {
            previewView.post {
                startCamera()
            }
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, 10)
        }

        setupButtons()
        initObservers()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            if (allPermissionsGranted()) {
                previewView.post { startCamera() }
            } else {
                Toast.makeText(
                    this.applicationContext,
                    "Veuillez autoriser les permissions de la Caméra.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this.applicationContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupButtons() {
        binding.closeButton.setOnClickListener {
            finish()
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(this) { modelUi ->
            when (modelUi.state) {
                CameraViewModel.CameraModelUi.State.Init -> {
                    binding.previewViewBottomText.text =
                        "Placer le code à barre dans la zone rectangulaire"
                    startHeartbeatAnimation()
                }

                CameraViewModel.CameraModelUi.State.Loading -> {
                    // Should play loading animation for a few seconds.
                    // This loading has no value but to let the user
                    // know that something is happening with the scanned barcode.
                    binding.previewViewBottomText.text = "Analyse du code à barre en cours..."
                    startLoadingAnimation()
                }

                CameraViewModel.CameraModelUi.State.Success -> {
                    // Should validate the Barcode and navigate back
                    loadingAnimator.cancel()
                    viewModel.handleSuccess()
                    finish()
                }
            }
        }
    }

    private fun startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this.applicationContext)
        cameraProviderFuture.addListener({
            val cameraProvider = try {
                cameraProviderFuture.get()
            } catch (e: ExecutionException) {
                throw IllegalStateException("Camera initialization failed.", e.cause)
            }
            bindPreviewCamera(cameraProvider)
        }, ContextCompat.getMainExecutor(this.applicationContext))
    }

    private fun bindPreviewCamera(cameraProvider: ProcessCameraProvider?) {
        val metrics = DisplayMetrics().also { previewView.display.getRealMetrics(it) }
        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)

        val rotation = previewView.display.rotation

        val preview = Preview.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(rotation)
            .build()

        val imageCapture = ImageCapture.Builder().build()

        val cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

        val imageAnalyser = ImageAnalysis.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(rotation)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(
                    cameraExecutor,
                    BarcodeAnalyser(
                        onBarcodeDetected = { barcodes ->
                            barcodes.forEach { barcode ->
                                barcode.rawValue?.let { barcodeValue ->
                                    viewModel.onBarcodeFound(barcodeValue)
                                }
                            }
                        },
                        widthCropPercent = DESIRED_WIDTH_CROP_PERCENT,
                        heightCropPercent = DESIRED_HEIGHT_CROP_PERCENT
                    )
                )
            }

        try {
            // Unbind use cases before rebinding
            cameraProvider?.unbindAll()

            // Bind use cases to camera
            val camera = cameraProvider?.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageAnalyser,
                imageCapture
            )

            // Starts reticule animation
            viewModel.onShowReticuleAnimation()
            cameraControl = camera?.cameraControl
            // Starts camera preview
            preview.setSurfaceProvider(previewView.surfaceProvider)
        } catch (e: Exception) {
            Log.e("CameraActivity", "Use case binding failed.")
        }
    }

    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = ln(max(width, height).toDouble() / min(width, height))
        if (abs(previewRatio - ln(4.0 / 3.0)) <= abs(previewRatio - ln(16.0 / 9.0))) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    private fun startLoadingAnimation() {
        // Before loading starts we want to Pause the camera:
        cameraProviderFuture.get()?.unbindAll()
        // Cancel the heartbeat animation:
        cameraReticuleAnimator.cancel()
        // Start the loading animation:
        loadingAnimator = createLoadingAnimator()
        loadingAnimator.start()
        // Provide the new graphic to the canvas:
        graphicOverlay.clear()
        graphicOverlay.add(
            CameraLoadingGraphic(
                context = this.applicationContext,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                animator = loadingAnimator
            )
        )
        graphicOverlay.invalidate()
    }

    private fun createLoadingAnimator(): ValueAnimator {
        return ValueAnimator.ofFloat(0f, 1f).apply {
            duration = LOADING_ANIMATION_DURATION
            addUpdateListener {
                if ((animatedValue as Float) >= 1f) {
                    graphicOverlay.clear()
                    viewModel.onScannedBarcodeLoadingFinished()
                } else {
                    graphicOverlay.invalidate()
                }
            }
        }
    }

    private fun startHeartbeatAnimation() {
        cameraReticuleAnimator = CameraReticuleAnimator(graphicOverlay)
        cameraReticuleAnimator.start()
        graphicOverlay.clear()
        graphicOverlay.add(
            CameraReticuleGraphic(
                context = this.applicationContext,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                animator = cameraReticuleAnimator
            )
        )
        graphicOverlay.invalidate()
    }

    override fun onResume() {
        super.onResume()
        allPermissionsGranted()
    }

    override fun onDestroy() {
        cameraControl = null
        cameraExecutor.shutdown()
        _binding = null
        super.onDestroy()
    }
}
