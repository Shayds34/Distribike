package com.distribike.features.subfeatures.form.scanner.main

import android.annotation.SuppressLint
import android.graphics.Rect
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.distribike.features.subfeatures.form.scanner.utils.ImageUtils.convertYuv420888ImageToBitmap
import com.distribike.features.subfeatures.form.scanner.utils.ImageUtils.rotateAndCrop
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

@SuppressLint("UnsafeOptInUsageError")
class BarcodeAnalyser(
    private val onBarcodeDetected: (barcodes: List<Barcode>) -> Unit,
    private val widthCropPercent: Int,
    private val heightCropPercent: Int
) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
        val image = imageProxy.image ?: return
        val imageBitmap = convertYuv420888ImageToBitmap(image)

        val cropRect = Rect(0, 0, image.width, image.height)
        cropRect.inset(
            (image.width * heightCropPercent / 100f / 2f).toInt(),
            (image.height * widthCropPercent / 100f / 2f).toInt()
        )

        val croppedImage = rotateAndCrop(
            bitmap = imageBitmap,
            cropRect = cropRect,
            imageRotationDegrees = imageProxy.imageInfo.rotationDegrees
        )

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_ITF,
                Barcode.FORMAT_CODE_39,
                Barcode.FORMAT_CODE_128,
                Barcode.FORMAT_CODE_93
            )
            .build()
        val barcodeScanner = BarcodeScanning.getClient(options)

        val imageToProcess =
            InputImage.fromBitmap(croppedImage, 0)

        barcodeScanner.process(imageToProcess)
            .addOnSuccessListener { barcodes ->
                Log.d("TAG", "barcodes $barcodes")
                if (barcodes.isNotEmpty()) {
                    onBarcodeDetected(barcodes)
                } else {
                    Log.d("TAG", "analyze: No barcode Scanned")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "BarcodeAnalyser: Something went wrong $exception")
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}