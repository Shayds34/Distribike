package com.distribike.features.subfeatures.form.main.model

data class FormModelUi(
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
    val additionalInfo: String?,
    val additionalInfo2: String?
)