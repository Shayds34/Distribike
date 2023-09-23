package com.distribike.features.subfeatures.pdf.usecase.model

data class StepUseCaseModel(
    val stepId: Int,
    val stepStateUseCaseModel: StepStateUseCaseModel = StepStateUseCaseModel.NONE,
    val additionalInfo: String? = null,
    val additionalInfo2: String? = null
)