package com.distribike.features.subfeatures.pdf.mapper

import com.distribike.features.subfeatures.motorcycleform.model.MotorcycleFormModelUi
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleUseCaseModel
import com.distribike.features.subfeatures.pdf.model.*
import com.distribike.features.subfeatures.pdf.usecase.model.PDFUseCaseModel
import com.distribike.features.subfeatures.pdf.usecase.model.StepStateUseCaseModel
import javax.inject.Inject

class PDFMapperUi @Inject constructor() {
    fun mapToModelUi(useCaseModel: PDFUseCaseModel): PDFModelUi {
        return PDFModelUi(
            generalSteps = GeneralModelUi(
                stepModelUis = useCaseModel.generalSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), batterySteps = BatteryModelUi(
                stepModelUis = useCaseModel.batterySteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), wheelsAndTiresSteps = WheelsAndTiresModelUi(
                stepEntityModels = useCaseModel.wheelsAndTiresSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), breaksSteps = BreaksModelUi(
                stepEntityModels = useCaseModel.breaksSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), suspensionSteps = SuspensionsModelUi(
                stepEntityModels = useCaseModel.suspensionSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), transmissionSteps = TransmissionModelUi(
                stepEntityModels = useCaseModel.transmissionSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), coolingSystemSteps = CoolingSystemModelUi(
                stepEntityModels = useCaseModel.coolingSystemSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), engineSteps = EngineModelUi(
                stepEntityModels = useCaseModel.engineSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), poweringSteps = PoweringModelUi(
                stepEntityModels = useCaseModel.poweringSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), clutchSteps = ClutchModelUi(
                stepEntityModels = useCaseModel.clutchSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), othersSteps = OthersModelUi(
                stepEntityModels = useCaseModel.othersSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), electricSystemSteps = ElectricSystemModelUi(
                stepEntityModels = useCaseModel.electricSystemSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            ), steeringSteps = SteeringModelUi(
                stepEntityModels = useCaseModel.steeringSteps.stepUseCaseModels?.map { step ->
                    PDFModelUi.StepModelUi(
                        stepId = step.stepId,
                        stepStateUseCaseModel = mapToState(step.stepStateUseCaseModel),
                        additionalInfo = step.additionalInfo,
                        additionalInfo2 = step.additionalInfo2
                    )
                }
            )
        )
    }

    private fun mapToState(
        state: StepStateUseCaseModel
    ): PDFModelUi.State {
        return when (state) {
            StepStateUseCaseModel.NONE -> PDFModelUi.State.NONE
            StepStateUseCaseModel.COMPLETE -> PDFModelUi.State.COMPLETE
            StepStateUseCaseModel.PASS -> PDFModelUi.State.PASS
        }
    }

    fun mapToMotorcycleModelUi(model: MotorcycleUseCaseModel): MotorcycleFormModelUi {
        return MotorcycleFormModelUi(
            username = model.username,
            codePrep = model.codePrep,
            model = model.model,
            chassis = model.chassis,
            concessionName = model.concessionName,
            concessionCode = model.concessionCode,
            positionNumber = model.positionNumber
        )
    }
}