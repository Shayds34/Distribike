package com.distribike.features.subfeatures.form.main.mapper

import com.distribike.features.subfeatures.form.main.model.FormModelUi
import com.distribike.features.subfeatures.form.main.model.Section
import com.distribike.features.subfeatures.form.main.model.Task
import com.distribike.features.subfeatures.form.usecase.model.SectionsUseCaseModel
import javax.inject.Inject

class FormUiMapper @Inject constructor() {

    fun mapToModelUi(useCaseModel: SectionsUseCaseModel) =
        FormModelUi(
            sections = useCaseModel.sections.map { section ->
                Section(
                    title = section.title,
                    tasks = section.tasks.map { task ->
                        Task(
                            id = task.id,
                            title = task.title,
                            description = task.description,
                            additionalInfo = task.additionalInfo,
                            additionalInfo2 = task.additionalInfo2
                        )
                    }
                )
            }
        )
}