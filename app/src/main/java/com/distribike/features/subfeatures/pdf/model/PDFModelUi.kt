package com.distribike.features.subfeatures.pdf.model

data class PDFModelUi(
    val generalSteps: GeneralModelUi? = null,
    val batterySteps: BatteryModelUi? = null,
    val wheelsAndTiresSteps: WheelsAndTiresModelUi? = null,
    val breaksSteps: BreaksModelUi? = null,
    val suspensionSteps: SuspensionsModelUi? = null,
    val transmissionSteps: TransmissionModelUi? = null,
    val coolingSystemSteps: CoolingSystemModelUi? = null,
    val engineSteps: EngineModelUi? = null,
    val poweringSteps: PoweringModelUi? = null,
    val clutchSteps: ClutchModelUi? = null,
    val othersSteps: OthersModelUi? = null,
    val electricSystemSteps: ElectricSystemModelUi? = null,
    val steeringSteps: SteeringModelUi? = null
) {

    data class StepModelUi(
        val stepId: Int,
        val stepStateUseCaseModel: State = State.NONE,
        val additionalInfo: String? = null,
        val additionalInfo2: String? = null
    )

    enum class State {
        NONE,
        COMPLETE,
        PASS
    }
}
