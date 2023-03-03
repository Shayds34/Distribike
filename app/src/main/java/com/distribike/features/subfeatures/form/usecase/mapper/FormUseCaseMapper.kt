package com.distribike.features.subfeatures.form.usecase.mapper

import com.distribike.features.subfeatures.form.entity.models.StepStateEntityModel
import com.distribike.features.subfeatures.form.main.component.StepState
import com.distribike.features.subfeatures.form.repository.model.SectionsRepositoryModel
import com.distribike.features.subfeatures.form.usecase.FormUseCaseImpl
import com.distribike.features.subfeatures.form.usecase.model.Section
import com.distribike.features.subfeatures.form.usecase.model.SectionsUseCaseModel
import com.distribike.features.subfeatures.form.usecase.model.Task
import javax.inject.Inject

class FormUseCaseMapper @Inject constructor() {
    fun mapToUseCaseModel(tasks: SectionsRepositoryModel) = SectionsUseCaseModel(
        sections = tasks.sections.map { section ->
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

    fun mapRepoToEntity(
        tasks: SectionsRepositoryModel
    ) = FormUseCaseImpl.SectionsEntityModel(
        sections = tasks.sections.map { section ->
            FormUseCaseImpl.Section(
                title = section.title,
                tasks = section.tasks.map { task ->
                    FormUseCaseImpl.Task(
                        id = task.id,
                        title = task.title,
                        description = task.description,
                        additionalInfo = task.additionalInfo,
                        additionalInfo2 = task.additionalInfo2,
                    )
                }
            )
        }
    )

    fun mapState(state: StepState): StepStateEntityModel {
        return when (state) {
            StepState.ERROR,
            StepState.TODO -> StepStateEntityModel.NONE
            StepState.COMPLETE -> StepStateEntityModel.COMPLETE
            StepState.PASS -> StepStateEntityModel.PASS
        }
    }
}
