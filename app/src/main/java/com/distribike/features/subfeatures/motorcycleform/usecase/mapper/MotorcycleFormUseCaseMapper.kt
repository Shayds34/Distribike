package com.distribike.features.subfeatures.motorcycleform.usecase.mapper

import com.distribike.features.subfeatures.motorcycleform.repository.model.MotorcycleectionsRepositoryModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.Section
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleSectionsUseCaseModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.Task
import javax.inject.Inject

class MotorcycleFormUseCaseMapper @Inject constructor() {

    fun mapToUseCaseModel(tasks: MotorcycleectionsRepositoryModel) = MotorcycleSectionsUseCaseModel(
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
}