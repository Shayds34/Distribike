package com.distribike.features.subfeatures.form.entity.mappers

import com.distribike.features.subfeatures.form.entity.models.*
import com.distribike.features.subfeatures.form.usecase.FormUseCaseImpl
import javax.inject.Inject

class FormsEntityMapper @Inject constructor() {

    //region GENERAL SECTION
    fun mapToGeneral(section: FormUseCaseImpl.Section): GeneralEntityModel {
        return GeneralEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToGeneralUpdatedStep(
        oldModel: GeneralEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region BATTERY SECTION
    fun mapToBattery(section: FormUseCaseImpl.Section): BatteryEntityModel {
        return BatteryEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToBatteryUpdatedStep(
        oldModel: BatteryEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region WHEELS SECTION
    fun mapToWheels(section: FormUseCaseImpl.Section): WheelsAndTiresEntityModel {
        return WheelsAndTiresEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToWheelsUpdatedStep(
        oldModel: WheelsAndTiresEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region BREAKS SECTION
    fun mapToBreaks(section: FormUseCaseImpl.Section): BreaksEntityModel {
        return BreaksEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToBreaksUpdatedStep(
        oldModel: BreaksEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region SUSPENSIONS SECTION
    fun mapToSuspension(section: FormUseCaseImpl.Section): SuspensionEntityModel {
        return SuspensionEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToSuspensionUpdatedStep(
        oldModel: SuspensionEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region TRANSMISSION SECTION
    fun mapToTransmission(section: FormUseCaseImpl.Section): TransmissionEntityModel {
        return TransmissionEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToTransmissionUpdatedStep(
        oldModel: TransmissionEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region COOLING SYSTEM SECTION
    fun mapToCoolingSystem(section: FormUseCaseImpl.Section): CoolingSystemEntityModel {
        return CoolingSystemEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToCoolingSystemUpdatedStep(
        oldModel: CoolingSystemEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region ENGINE SECTION
    fun mapToEngine(section: FormUseCaseImpl.Section): EngineEntityModel {
        return EngineEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToEngineUpdatedStep(
        oldModel: EngineEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region POWERING SECTION
    fun mapToPowering(section: FormUseCaseImpl.Section): PoweringEntityModel {
        return PoweringEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToPoweringUpdatedStep(
        oldModel: PoweringEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region CLUTCH SECTION
    fun mapToClutch(section: FormUseCaseImpl.Section): ClutchEntityModel {
        return ClutchEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToClutchUpdatedStep(
        oldModel: ClutchEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region OTHERS SECTION
    fun mapToOthers(section: FormUseCaseImpl.Section): OthersEntityModel {
        return OthersEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToOthersUpdatedStep(
        oldModel: OthersEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region ELECTRIC SYSTEM SECTION
    fun mapToElectricSystem(section: FormUseCaseImpl.Section): ElectricSystemEntityModel {
        return ElectricSystemEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToElectricSystemUpdatedStep(
        oldModel: ElectricSystemEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    //region STEERING SECTION
    fun mapToSteering(section: FormUseCaseImpl.Section): SteeringEntityModel {
        return SteeringEntityModel(
            stepEntityModels = section.tasks.map { task ->
                StepEntityModel(
                    stepId = task.id,
                    stepStateEntityModel = StepStateEntityModel.NONE,
                    additionalInfo = task.additionalInfo,
                    additionalInfo2 = task.additionalInfo2
                )
            }
        )
    }

    fun mapToSteeringUpdatedStep(
        oldModel: SteeringEntityModel?,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = oldModel?.copy(
        stepEntityModels = oldModel.stepEntityModels?.map { step ->
            mapUpdatedStep(
                step,
                currentStep,
                stepStateEntityModel,
                additionalInfo,
                additionalInfo2
            )
        }
    )
    //endregion

    /**
     * Generic map to a new updated step.
     * Shared by all Sections.
     */
    private fun mapUpdatedStep(
        stepEntityModel: StepEntityModel,
        currentStep: Int,
        stepStateEntityModel: StepStateEntityModel,
        additionalInfo: String?,
        additionalInfo2: String?
    ) = stepEntityModel.takeUnless { stepEntityModel.stepId == currentStep }
        ?: stepEntityModel.copy(
            stepStateEntityModel = stepStateEntityModel,
            additionalInfo = additionalInfo,
            additionalInfo2 = additionalInfo2
        )
}
