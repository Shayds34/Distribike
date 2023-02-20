package com.distribike.features.subfeatures.form.usecase

import com.distribike.features.subfeatures.form.main.component.StepState
import com.distribike.features.subfeatures.form.usecase.model.SectionsUseCaseModel
import kotlinx.coroutines.flow.Flow

interface FormUseCase {

    val isGeneralFormCompleted: Flow<Boolean>
    val isBatteryFormCompleted: Flow<Boolean>
    val isWheelsAndTiresFormCompleted: Flow<Boolean>
    val isBreaksFormCompleted: Flow<Boolean>
    val isSuspensionFormCompleted: Flow<Boolean>
    val isTransmissionFormCompleted: Flow<Boolean>
    val isCoolingSystemFormCompleted: Flow<Boolean>
    val isEngineFormCompleted: Flow<Boolean>
    val isPoweringFormCompleted: Flow<Boolean>
    val isClutchFormCompleted: Flow<Boolean>
    val isOtherFormCompleted: Flow<Boolean>
    val isElectricSystemFormCompleted: Flow<Boolean>
    val isSteeringFormCompleted: Flow<Boolean>

    /**
     * Retrieve a list of tasks to display
     */
    suspend fun getTasks(): SectionsUseCaseModel

    /**
     * Save the current state for a specific state,
     * for GENERAL section retrieved by its index.
     */
    suspend fun saveGeneralStepState(
        state: StepState,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a specific state,
     * for BATTERY section retrieved by its index.
     */
    suspend fun saveBatteryStepState(
        state: StepState,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )
}