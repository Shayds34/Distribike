package com.distribike.features.subfeatures.motorcycleform.usecase

import com.distribike.features.subfeatures.motorcycleform.entity.MotorcycleEntity
import com.distribike.features.subfeatures.motorcycleform.repository.MotorcycleFormRepository
import com.distribike.features.subfeatures.motorcycleform.usecase.mapper.MotorcycleFormUseCaseMapper
import com.distribike.features.subfeatures.motorcycleform.usecase.model.ClientsUseCaseModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleUseCaseModel
import com.distribike.modules.DispatchersName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * UseCase that handles interactions with the tasks json file.
 */
class MotorcycleFormUseCaseImpl @Inject constructor(
    private val mapper: MotorcycleFormUseCaseMapper,
    private val repository: MotorcycleFormRepository,
    private val entity: MotorcycleEntity,
    @Named(DispatchersName.DOMAIN) private val dispatcher: CoroutineDispatcher
) : MotorcycleFormUseCase {

    /**
     * Retrieve a list of clients to display.
     */
    override suspend fun getClients(): ClientsUseCaseModel =
        withContext(dispatcher) {
            val clients = repository.getClients()
            mapper.mapToUseCaseModel(
                repoModel = clients
            )
        }

    /**
     * Retrieve a specific client by its code.
     */
    override suspend fun getClientWithCode(code: String): String? =
        withContext(dispatcher) {
            val clients = repository.getClients()
            mapper.mapToClientName(
                code = code,
                model = clients
            )
        }

    /**
     * Save Motorcycle form data into entity to be
     * retrieved in PDFActivity later on.
     */
    override suspend fun storeMotorcycleData(model: MotorcycleUseCaseModel) =
        withContext(dispatcher) {
            entity.storeMotorcycleForm(
                model = mapper.mapToFormEntityModel(model)
            )
        }
}
