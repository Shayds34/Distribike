package com.distribike.features.subfeatures.motorcycleform.usecase

import com.distribike.features.subfeatures.motorcycleform.repository.MotorcycleFormRepository
import com.distribike.features.subfeatures.motorcycleform.usecase.mapper.MotorcycleFormUseCaseMapper
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleSectionsUseCaseModel
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
    @Named(DispatchersName.DOMAIN) private val dispatcher: CoroutineDispatcher
) : MotorcycleFormUseCase {

    /**
     * Retrieve a list of tasks to display.
     */
    override suspend fun getTasks(): MotorcycleSectionsUseCaseModel =
        withContext(dispatcher) {
            val tasks = repository.getTasks()
            mapper.mapToUseCaseModel(tasks)
        }
}
