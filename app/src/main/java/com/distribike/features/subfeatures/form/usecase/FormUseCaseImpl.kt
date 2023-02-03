package com.distribike.features.subfeatures.form.usecase

import com.distribike.features.subfeatures.form.repository.FormRepository
import com.distribike.features.subfeatures.form.usecase.mapper.FormUseCaseMapper
import com.distribike.features.subfeatures.form.usecase.model.SectionsUseCaseModel
import com.distribike.modules.DispatchersName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * UseCase that handles interactions with the tasks json file.
 */
class FormUseCaseImpl @Inject constructor(
    private val mapper: FormUseCaseMapper,
    private val repository: FormRepository,
    @Named(DispatchersName.DOMAIN) private val dispatcher: CoroutineDispatcher
) : FormUseCase {

    /**
     * Retrieve a list of tasks to display.
     */
    override suspend fun getTasks(): SectionsUseCaseModel =
        withContext(dispatcher) {
            val tasks = repository.getTasks()
            mapper.mapToUseCaseModel(tasks)
        }
}
