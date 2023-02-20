package com.distribike.features.subfeatures.form.usecase

import com.distribike.features.subfeatures.form.entity.FormSaverEntity
import com.distribike.features.subfeatures.form.entity.models.StepStateEntityModel
import com.distribike.features.subfeatures.form.main.component.StepState
import com.distribike.features.subfeatures.form.repository.FormRepository
import com.distribike.features.subfeatures.form.repository.model.SectionsRepositoryModel
import com.distribike.features.subfeatures.form.usecase.mapper.FormUseCaseMapper
import com.distribike.features.subfeatures.form.usecase.model.SectionsUseCaseModel
import com.distribike.modules.DispatchersName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 * UseCase that handles interactions with the tasks json file.
 */
class FormUseCaseImpl @Inject constructor(
    private val mapper: FormUseCaseMapper,
    private val repository: FormRepository,
    private val entity: FormSaverEntity,
    @Named(DispatchersName.DOMAIN) private val dispatcher: CoroutineDispatcher
) : FormUseCase {

    override val isGeneralFormCompleted: Flow<Boolean> = entity.isGeneralFormCompleted
    override val isBatteryFormCompleted: Flow<Boolean> = entity.isBatteryFormCompleted
    override val isWheelsAndTiresFormCompleted: Flow<Boolean> = entity.isWheelsAndTiresFormCompleted
    override val isBreaksFormCompleted: Flow<Boolean> = entity.isBreaksFormCompleted
    override val isSuspensionFormCompleted: Flow<Boolean> = entity.isSuspensionFormCompleted
    override val isTransmissionFormCompleted: Flow<Boolean> = entity.isTransmissionFormCompleted
    override val isCoolingSystemFormCompleted: Flow<Boolean> = entity.isCoolingSystemFormCompleted
    override val isEngineFormCompleted: Flow<Boolean> = entity.isEngineFormCompleted
    override val isPoweringFormCompleted: Flow<Boolean> = entity.isPoweringFormCompleted
    override val isClutchFormCompleted: Flow<Boolean> = entity.isClutchFormCompleted
    override val isOtherFormCompleted: Flow<Boolean> = entity.isOtherFormCompleted
    override val isElectricSystemFormCompleted: Flow<Boolean> = entity.isElectricSystemFormCompleted
    override val isSteeringFormCompleted: Flow<Boolean> = entity.isSteeringFormCompleted

    /**
     * Retrieve a list of tasks to display.
     */
    override suspend fun getTasks(): SectionsUseCaseModel =
        withContext(dispatcher) {
            val tasks = repository.getTasks()
            entity.storeTasks(model = mapper.mapRepoToEntity(tasks))
            mapper.mapToUseCaseModel(tasks = tasks)
        }

    data class SectionsEntityModel(
        val sections: List<Section>
    )

    data class Section(
        val title: String,
        val tasks: List<Task>
    )

    data class Task(
        val id: Int,
        val title: String,
        val description: String,
        val additionalInfo: String? = null,
        val additionalInfo2: String? = null
    )

    override suspend fun saveGeneralStepState(
        state: StepState,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        entity.saveGeneralCurrentStepState(
            stepStateEntityModel = mapper.mapState(state = state),
            currentStep = currentStep,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )
    }

    override suspend fun saveBatteryStepState(
        state: StepState,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        entity.saveBatteryCurrentStepState(
            stepStateEntityModel = mapper.mapState(state = state),
            currentStep = currentStep,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )
    }
}
