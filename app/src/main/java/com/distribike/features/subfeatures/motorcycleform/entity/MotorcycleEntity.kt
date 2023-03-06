package com.distribike.features.subfeatures.motorcycleform.entity

import com.distribike.features.subfeatures.motorcycleform.entity.model.MotorcycleEntityModel
import kotlinx.coroutines.flow.StateFlow

interface MotorcycleEntity {

    val motorcycleForm: StateFlow<MotorcycleEntityModel>

    fun storeMotorcycleForm(model: MotorcycleEntityModel)
    fun clearExceptUserName()
}
