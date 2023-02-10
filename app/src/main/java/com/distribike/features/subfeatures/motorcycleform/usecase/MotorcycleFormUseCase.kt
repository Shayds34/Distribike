package com.distribike.features.subfeatures.motorcycleform.usecase

import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleSectionsUseCaseModel

interface MotorcycleFormUseCase {

    /**
     * Retrieve a list of tasks to display
     */
    suspend fun getTasks(): MotorcycleSectionsUseCaseModel
}