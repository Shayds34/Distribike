package com.distribike.features.subfeatures.form.usecase.model

data class SectionsUseCaseModel(
    val sections: List<Section>
)

data class Section(
    val title: String,
    val tasks: List<Task>
)

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val additionalInfo: String? = null,
    val additionalInfo2: String? = null
)