package com.distribike.features.subfeatures.form.entity.models

data class FormRecordEntityModel(
    val generalSteps: GeneralEntityModel? = null,
    val batterySteps: BatteryEntityModel? = null,
    val wheelsAndTiresSteps: WheelsAndTiresEntityModel? = null,
    val breaksSteps: BreaksEntityModel? = null,
    val suspensionSteps: SuspensionEntityModel? = null,
    val transmissionSteps: TransmissionEntityModel? = null,
    val coolingSystemSteps: CoolingSystemEntityModel? = null,
    val engineSteps: EngineEntityModel? = null,
    val poweringSteps: PoweringEntityModel? = null,
    val clutchSteps: ClutchEntityModel? = null,
    val othersSteps: OthersEntityModel? = null,
    val electricSystemSteps: ElectricSystemEntityModel? = null,
    val steeringSteps: SteeringEntityModel? = null,
)
