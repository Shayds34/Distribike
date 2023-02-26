package com.distribike.features.subfeatures.pdf.model

data class PDFModelUi(
    val generalSteps: GeneralModelUi,
    val batterySteps: BatteryModelUi,
    val wheelsAndTiresSteps: WheelsAndTiresModelUi,
    val breaksSteps: BreaksModelUi,
    val suspensionSteps: SuspensionsModelUi,
    val transmissionSteps: TransmissionModelUi,
    val coolingSystemSteps: CoolingSystemModelUi,
    val engineSteps: EngineModelUi,
    val poweringSteps: PoweringModelUi,
    val clutchSteps: ClutchModelUi,
    val othersSteps: OthersModelUi,
    val electricSystemSteps: ElectricSystemModelUi,
    val steeringSteps: SteeringModelUi
) {

    data class StepModelUi(
        val stepId: Int,
        val stepStateUseCaseModel: StepStateUseCaseModel = StepStateUseCaseModel.NONE,
        val additionalInfo: String? = null,
        val additionalInfo2: String? = null
    )

    enum class StepStateUseCaseModel {
        NONE,
        COMPLETE,
        PASS
    }
}
