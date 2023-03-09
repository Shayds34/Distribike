package com.distribike.features.subfeatures.form.scanner.main

import android.Manifest
import android.app.ActionBar.LayoutParams
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.distribike.features.subfeatures.form.scanner.main.component.BarcodeConfirmationDialog
import com.distribike.features.subfeatures.form.scanner.main.viewmodel.CameraViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class CameraActivity : ComponentActivity(), LifecycleOwner {

    private val viewModel: CameraViewModel by viewModels()

    companion object {
        fun newInstance(context: Context) = Intent(context, CameraActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                val cameraPermissionState =
                    rememberPermissionState(permission = Manifest.permission.CAMERA)

                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text(text = "Camera Permissions")
                }

                CameraPreview()

                BarcodeConfirmationDialog()

                if (viewModel.chassisState.collectAsState().value.isNotEmpty()) {
                    finish()
                }
            }
        }
    }

    @Composable
    fun CameraPreview(
        viewModel: CameraViewModel = hiltViewModel()
    ) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        var preview by remember { mutableStateOf<Preview?>(null) }

        Box(
            modifier = Modifier.fillMaxSize().wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = { AndroidViewContext ->
                    PreviewView(AndroidViewContext).apply {
                        this.scaleType = PreviewView.ScaleType.FILL_CENTER
                        layoutParams = FrameLayout.LayoutParams(
                            600,
                            200,
                            Gravity.CENTER
                        )
                        implementationMode = PreviewView.ImplementationMode.PERFORMANCE
                    }
                },
                modifier = Modifier.size(600.dp, 200.dp),
                update = { previewView ->
                    val cameraSelector: CameraSelector =
                        CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                    val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
                    val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                        ProcessCameraProvider.getInstance(context)

                    cameraProviderFuture.addListener({
                        preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        val screenWidth = applicationContext.resources.displayMetrics.widthPixels
                        val screenHeight = applicationContext.resources.displayMetrics.heightPixels

                        val width = screenHeight / 2
                        val height = screenWidth / 2

                        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                        val barcodeAnalyser = BarcodeAnalyser { barcodes ->
                            barcodes.forEach { barcode ->
                                barcode.rawValue?.let { barcodeValue ->
                                    viewModel.showDialog(barcodeValue)
                                }
                            }
                        }

                        val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .setTargetResolution(android.util.Size(width, height)).build().also {
                                it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                            }

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner, cameraSelector, preview, imageAnalysis
                            )
                        } catch (e: Exception) {
                            Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                        }
                    }, ContextCompat.getMainExecutor(context))
                })
        }
    }
}
