package com.distribike.features.subfeatures.pdf.usecase.model

data class PDFUseCaseModel(
    val generalSteps: GeneralUseCaseModel,
    val batterySteps: BatteryUseCaseModel,
    val wheelsAndTiresSteps: WheelsAndTiresUseCaseModel,
    val breaksSteps: BreaksUseCaseModel,
    val suspensionSteps: SuspensionsUseCaseModel,
    val transmissionSteps: TransmissionUseCaseModel,
    val coolingSystemSteps: CoolingSystemUseCaseModel,
    val engineSteps: EngineUseCaseModel,
    val poweringSteps: PoweringUseCaseModel,
    val clutchSteps: ClutchUseCaseModel,
    val othersSteps: OthersUseCaseModel,
    val electricSystemSteps: ElectricSystemUseCaseModel,
    val steeringSteps: SteeringUseCaseModel,
)