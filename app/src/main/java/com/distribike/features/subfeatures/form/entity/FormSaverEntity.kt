package com.distribike.features.subfeatures.form.entity

import com.distribike.features.subfeatures.form.entity.models.FormRecordEntityModel
import com.distribike.features.subfeatures.form.entity.models.StepStateEntityModel
import com.distribike.features.subfeatures.form.usecase.FormUseCaseImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface FormSaverEntity {

    val formRecord: StateFlow<FormRecordEntityModel>

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

    val getPDFResult: Flow<FormRecordEntityModel>

    /**
     * Save the current state of a form to retrieve
     * data later in the process.
     */
    suspend fun storeTasks(model: FormUseCaseImpl.SectionsEntityModel)

    /**
     * Save the current state for a step in the GENERAL section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveGeneralCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the BATTERY section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveBatteryCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the WHEELS AND TIRES section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveWheelsAndTiresCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the BREAKS section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveBreaksCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the SUSPENSION section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveSuspensionCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the TRANSMISSION section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveTransmissionCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the COOLING SYSTEM section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveCoolingSystemCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the ENGINE section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveEngineCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the POWERING section,
     * retrieved by its index in the steps list.
     */
    suspend fun savePoweringCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the CLUTCH section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveClutchCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the OTHERS section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveOthersCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the ELECTRIC SYSTEM section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveElectricSystemCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )

    /**
     * Save the current state for a step in the STEERING section,
     * retrieved by its index in the steps list.
     */
    suspend fun saveSteeringCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    )
}