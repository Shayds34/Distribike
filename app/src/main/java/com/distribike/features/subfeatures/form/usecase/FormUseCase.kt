package com.distribike.features.subfeatures.form.usecase

import com.distribike.features.subfeatures.form.usecase.model.SectionsUseCaseModel

interface FormUseCase {

    /**
     * Retrieve a list of tasks to display
     */
    suspend fun getTasks(): SectionsUseCaseModel
}