package com.distribike.features.subfeatures.form.repository

import com.distribike.features.subfeatures.form.repository.model.SectionsRepositoryModel

interface FormRepository {

    /**
     * Retrieve a list of tasks to display
     */
    suspend fun getTasks(): SectionsRepositoryModel
}