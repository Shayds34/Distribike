package com.distribike.features.subfeatures.form.entity

import com.distribike.features.subfeatures.form.entity.mappers.FormsEntityMapper
import com.distribike.features.subfeatures.form.entity.models.FormRecordEntityModel
import com.distribike.features.subfeatures.form.entity.models.StepStateEntityModel
import com.distribike.features.subfeatures.form.usecase.FormUseCaseImpl
import com.distribike.utils.setState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class FormSaverEntityImpl @Inject constructor(
    private val formsEntityMapper: FormsEntityMapper
) : FormSaverEntity {

    private val _formRecord = MutableStateFlow(FormRecordEntityModel())
    override val formRecord: StateFlow<FormRecordEntityModel> = _formRecord.asStateFlow()

    /**
     * Save the current state of a form to retrieve
     * data later in the process.
     */
    override suspend fun storeTasks(model: FormUseCaseImpl.SectionsEntityModel) {
        _formRecord.value = _formRecord.value.copy(
            generalSteps = formsEntityMapper.mapToGeneral(model.sections[0]),
            batterySteps = formsEntityMapper.mapToBattery(model.sections[1]),
            wheelsAndTiresSteps = formsEntityMapper.mapToWheels(model.sections[2]),
            breaksSteps = formsEntityMapper.mapToBreaks(model.sections[3]),
            suspensionSteps = formsEntityMapper.mapToSuspension(model.sections[4]),
            transmissionSteps = formsEntityMapper.mapToTransmission(model.sections[5]),
            coolingSystemSteps = formsEntityMapper.mapToCoolingSystem(model.sections[6]),
            engineSteps = formsEntityMapper.mapToEngine(model.sections[7]),
            poweringSteps = formsEntityMapper.mapToPowering(model.sections[8]),
            clutchSteps = formsEntityMapper.mapToClutch(model.sections[9]),
            othersSteps = formsEntityMapper.mapToOthers(model.sections[10]),
            electricSystemSteps = formsEntityMapper.mapToElectricSystem(model.sections[11]),
            steeringSteps = formsEntityMapper.mapToSteering(model.sections[12]),
        )
    }

    /**
     * Clear all data to start a new form.
     */
    override fun clear() {
        _formRecord.value = FormRecordEntityModel()
    }

    //region GENERAL SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the GENERAL section in real time.
     */
    override val isGeneralFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.generalSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in GENERAL section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveGeneralCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val generalEntityModel = _formRecord.value.generalSteps

        val updatedStep = formsEntityMapper.mapToGeneralUpdatedStep(
            oldModel = generalEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                generalSteps = _formRecord.value.generalSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region BATTERY SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the BATTERY section in real time.
     */
    override val isBatteryFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.batterySteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in BATTERY section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveBatteryCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val batteryEntityModel = _formRecord.value.batterySteps

        val updatedStep = formsEntityMapper.mapToBatteryUpdatedStep(
            oldModel = batteryEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                batterySteps = _formRecord.value.batterySteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region WHEELS SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the WHEELs section in real time.
     */
    override val isWheelsAndTiresFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.wheelsAndTiresSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in WHEELS section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveWheelsAndTiresCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val wheelsEntityModel = _formRecord.value.wheelsAndTiresSteps

        val updatedStep = formsEntityMapper.mapToWheelsUpdatedStep(
            oldModel = wheelsEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                wheelsAndTiresSteps = _formRecord.value.wheelsAndTiresSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region BREAKS SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the BREAKS section in real time.
     */
    override val isBreaksFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.breaksSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in BREAKS section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveBreaksCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val breaksEntityModel = _formRecord.value.breaksSteps

        val updatedStep = formsEntityMapper.mapToBreaksUpdatedStep(
            oldModel = breaksEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                breaksSteps = _formRecord.value.breaksSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region SUSPENSIONS SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the SUSPENSIONS section in real time.
     */
    override val isSuspensionFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.suspensionSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in SUSPENSIONS section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveSuspensionCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val suspensionEntityModel = _formRecord.value.suspensionSteps

        val updatedStep = formsEntityMapper.mapToSuspensionUpdatedStep(
            oldModel = suspensionEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                suspensionSteps = _formRecord.value.suspensionSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region TRANSMISSION SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the TRANSMISSION section in real time.
     */
    override val isTransmissionFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.transmissionSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in TRANSMISSION section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveTransmissionCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val transmissionEntityModel = _formRecord.value.transmissionSteps

        val updatedStep = formsEntityMapper.mapToTransmissionUpdatedStep(
            oldModel = transmissionEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                transmissionSteps = _formRecord.value.transmissionSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region COOLING SYSTEM SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the COOLING section in real time.
     */
    override val isCoolingSystemFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.coolingSystemSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in COOLING section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveCoolingSystemCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val coolingEntityModel = _formRecord.value.coolingSystemSteps

        val updatedStep = formsEntityMapper.mapToCoolingSystemUpdatedStep(
            oldModel = coolingEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                coolingSystemSteps = _formRecord.value.coolingSystemSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region BATTERY SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the ENGINE section in real time.
     */
    override val isEngineFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.engineSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in ENGINE section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveEngineCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val engineEntityModel = _formRecord.value.engineSteps

        val updatedStep = formsEntityMapper.mapToEngineUpdatedStep(
            oldModel = engineEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                engineSteps = _formRecord.value.engineSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region POWERING SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the POWERING section in real time.
     */
    override val isPoweringFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.poweringSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in POWERING section,
     * retrieved by its index in the steps list.
     */
    override suspend fun savePoweringCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val poweringEntityModel = _formRecord.value.poweringSteps

        val updatedStep = formsEntityMapper.mapToPoweringUpdatedStep(
            oldModel = poweringEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                poweringSteps = _formRecord.value.poweringSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region BATTERY SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the CLUTCH section in real time.
     */
    override val isClutchFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.clutchSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in CLUTCH section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveClutchCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val clutchEntityModel = _formRecord.value.clutchSteps

        val updatedStep = formsEntityMapper.mapToClutchUpdatedStep(
            oldModel = clutchEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                clutchSteps = _formRecord.value.clutchSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region OTHERS SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the OTHERS section in real time.
     */
    override val isOtherFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.othersSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in OTHERS section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveOthersCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val othersEntityModel = _formRecord.value.othersSteps

        val updatedStep = formsEntityMapper.mapToOthersUpdatedStep(
            oldModel = othersEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                othersSteps = _formRecord.value.othersSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region ELECTRIC SYSTEM SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the ELECTRIC SYSTEM section in real time.
     */
    override val isElectricSystemFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.electricSystemSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in ELECTRIC SYSTEM section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveElectricSystemCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val electricEntityModel = _formRecord.value.electricSystemSteps

        val updatedStep = formsEntityMapper.mapToElectricSystemUpdatedStep(
            oldModel = electricEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                electricSystemSteps = _formRecord.value.electricSystemSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion

    //region BATTERY SECTION
    /**
     * Used to know is every step has been COMPLETED or PASS
     * for the STEERING section in real time.
     */
    override val isSteeringFormCompleted: Flow<Boolean> = formRecord
        .flatMapLatest { formRecord ->
            flow {
                val allComplete = formRecord.steeringSteps?.stepEntityModels?.all {
                    it.stepStateEntityModel != StepStateEntityModel.NONE
                }
                emit(allComplete == true)
            }
        }

    /**
     * Save the current state for a step in STEERING section,
     * retrieved by its index in the steps list.
     */
    override suspend fun saveSteeringCurrentStepState(
        stepStateEntityModel: StepStateEntityModel,
        currentStep: Int,
        additionalInfo: String?,
        additionalInfo2: String?
    ) {
        val steeringEntityModel = _formRecord.value.steeringSteps

        val updatedStep = formsEntityMapper.mapToSteeringUpdatedStep(
            oldModel = steeringEntityModel,
            currentStep = currentStep,
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )

        _formRecord.setState { value ->
            value.copy(
                steeringSteps = _formRecord.value.steeringSteps?.copy(
                    stepEntityModels = updatedStep?.stepEntityModels
                )
            )
        }
    }
    //endregion
}
