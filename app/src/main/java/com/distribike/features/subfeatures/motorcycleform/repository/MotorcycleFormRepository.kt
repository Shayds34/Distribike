package com.distribike.features.subfeatures.motorcycleform.repository

import com.distribike.features.subfeatures.motorcycleform.repository.model.ClientsRepositoryModel

interface MotorcycleFormRepository {

    /**
     * Retrieve a list of clients
     */
    suspend fun getClients(): ClientsRepositoryModel

}
