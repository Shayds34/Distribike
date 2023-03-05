package com.distribike.features.subfeatures.form.scanner.entity

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CameraEntityImpl @Inject constructor(
    // private val mapper: CameraEntityMapper
) : CameraEntity {

    private val _cameraBarcode = MutableStateFlow(String())
    override val cameraBarcode: StateFlow<String> = _cameraBarcode.asStateFlow()

    override fun saveCameraBarcode(barcode: String) {
        _cameraBarcode.value = barcode
    }
}
