package com.distribike.features.subfeatures.form.entity.models

data class StepEntityModel(
    val stepId: Int,
    val stepStateEntityModel: StepStateEntityModel = StepStateEntityModel.NONE,
    val additionalInfo: String? = null,
    val additionalInfo2: String? = null
)