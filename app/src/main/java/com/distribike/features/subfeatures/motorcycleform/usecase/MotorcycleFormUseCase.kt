package com.distribike.features.subfeatures.motorcycleform.usecase

import com.distribike.features.subfeatures.motorcycleform.usecase.model.ClientsUseCaseModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleUseCaseModel

interface MotorcycleFormUseCase {

    /**
     * Retrieve a list of clients
     */
    suspend fun getClients(): ClientsUseCaseModel

    /**
     * Retrieve client name by its code
     */
    suspend fun getClientWithCode(code: String): String?

    /**
     * Store Motorcycle from data into entity
     */
    suspend fun storeMotorcycleData(model: MotorcycleUseCaseModel)
}