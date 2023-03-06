package com.distribike.features.subfeatures.form.scanner.entity

import kotlinx.coroutines.flow.StateFlow

interface CameraEntity {

    val cameraBarcode: StateFlow<String>

    fun saveCameraBarcode(barcode: String)

    fun clear()
}
