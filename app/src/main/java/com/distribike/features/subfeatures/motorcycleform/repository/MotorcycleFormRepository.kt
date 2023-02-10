package com.distribike.features.subfeatures.motorcycleform.repository

import com.distribike.features.subfeatures.motorcycleform.repository.model.MotorcycleectionsRepositoryModel

interface MotorcycleFormRepository {

    /**
     * Retrieve a list of tasks to display
     */
    suspend fun getTasks(): MotorcycleectionsRepositoryModel
}