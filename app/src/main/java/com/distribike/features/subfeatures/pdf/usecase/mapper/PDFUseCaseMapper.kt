package com.distribike.features.subfeatures.pdf.usecase.mapper

import com.distribike.features.subfeatures.form.entity.models.FormRecordEntityModel
import com.distribike.features.subfeatures.form.entity.models.StepStateEntityModel
import com.distribike.features.subfeatures.pdf.usecase.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PDFUseCaseMapper @Inject constructor() {

    fun mapEntityToUseCaseModel(formRecordEntityModel: FormRecordEntityModel): PDFUseCaseModel {
        return PDFUseCaseModel(
            generalSteps = GeneralUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.generalSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), batterySteps = BatteryUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.batterySteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), wheelsAndTiresSteps = WheelsAndTiresUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.wheelsAndTiresSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), breaksSteps = BreaksUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.breaksSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), suspensionSteps = SuspensionsUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.suspensionSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), transmissionSteps = TransmissionUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.transmissionSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), coolingSystemSteps = CoolingSystemUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.coolingSystemSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), engineSteps = EngineUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.engineSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), poweringSteps = PoweringUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.poweringSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), clutchSteps = ClutchUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.clutchSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), othersSteps = OthersUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.othersSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), electricSystemSteps = ElectricSystemUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.electricSystemSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), steeringSteps = SteeringUseCaseModel(
                stepUseCaseModels = formRecordEntityModel.steeringSteps?.stepEntityModels?.map { step ->
                    StepUseCaseModel(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            )
        )
    }

    private fun mapToState(state: StepStateEntityModel): StepStateUseCaseModel {
        return when (state) {
            StepStateEntityModel.NONE -> StepStateUseCaseModel.NONE
            StepStateEntityModel.COMPLETE -> StepStateUseCaseModel.COMPLETE
            StepStateEntityModel.PASS -> StepStateUseCaseModel.PASS
        }
    }
}