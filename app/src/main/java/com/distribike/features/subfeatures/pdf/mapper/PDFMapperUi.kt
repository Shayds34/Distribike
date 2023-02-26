package com.distribike.features.subfeatures.pdf.mapper

import com.distribike.features.subfeatures.form.entity.models.FormRecordEntityModel
import com.distribike.features.subfeatures.form.entity.models.StepStateEntityModel
import com.distribike.features.subfeatures.pdf.model.*
import javax.inject.Inject

class PDFMapperUi @Inject constructor() {
    fun mapToModelUi(useCaseModel: FormRecordEntityModel): PDFModelUi {
        return PDFModelUi(
            generalSteps = GeneralModelUi(
                stepModelUis = useCaseModel.generalSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), batterySteps = BatteryModelUi(
                stepModelUis = useCaseModel.batterySteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), wheelsAndTiresSteps = WheelsAndTiresModelUi(
                stepEntityModels = useCaseModel.wheelsAndTiresSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), breaksSteps = BreaksModelUi(
                stepEntityModels = useCaseModel.breaksSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), suspensionSteps = SuspensionsModelUi(
                stepEntityModels = useCaseModel.suspensionSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), transmissionSteps = TransmissionModelUi(
                stepEntityModels = useCaseModel.transmissionSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), coolingSystemSteps = CoolingSystemModelUi(
                stepEntityModels = useCaseModel.coolingSystemSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), engineSteps = EngineModelUi(
                stepEntityModels = useCaseModel.engineSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), poweringSteps = PoweringModelUi(
                stepEntityModels = useCaseModel.poweringSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), clutchSteps = ClutchModelUi(
                stepEntityModels = useCaseModel.clutchSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), othersSteps = OthersModelUi(
                stepEntityModels = useCaseModel.othersSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), electricSystemSteps = ElectricSystemModelUi(
                stepEntityModels = useCaseModel.electricSystemSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), steeringSteps = SteeringModelUi(
                stepEntityModels = useCaseModel.steeringSteps?.stepEntityModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateEntityModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            )
        )
    }

    private fun mapToState(
        state: StepStateEntityModel
    ): PDFModelUi.StepStateUseCaseModel {
        return when (state) {
            StepStateEntityModel.NONE -> PDFModelUi.StepStateUseCaseModel.NONE
            StepStateEntityModel.COMPLETE -> PDFModelUi.StepStateUseCaseModel.COMPLETE
            StepStateEntityModel.PASS -> PDFModelUi.StepStateUseCaseModel.PASS
        }
    }
}