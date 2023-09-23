package com.distribike.features.subfeatures.motorcycleform.entity

import android.util.Log
import com.distribike.features.subfeatures.motorcycleform.entity.model.MotorcycleEntityModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MotorcycleEntityImpl @Inject constructor() : MotorcycleEntity {

    private val _motorcycleForm = MutableStateFlow(MotorcycleEntityModel())
    override val motorcycleForm: StateFlow<MotorcycleEntityModel> = _motorcycleForm.asStateFlow()

    override fun storeMotorcycleForm(model: MotorcycleEntityModel) {
        _motorcycleForm.value = _motorcycleForm.value.copy(
            username = model.username,
            codePrep = model.codePrep,
            model = model.model,
            chassis = model.chassis,
            concessionName = model.concessionName,
            concessionCode = model.concessionCode,
            positionNumber = model.positionNumber
        )
    }

    override fun clearExceptUserName() {
        _motorcycleForm.value = _motorcycleForm.value.copy(
            codePrep = null,
            model = null,
            chassis = null,
            concessionName = null,
            concessionCode = null,
            positionNumber = null
        )
    }

    override fun clearUsername() {
        _motorcycleForm.value = _motorcycleForm.value.copy(
            username = null
        )
    }
}
