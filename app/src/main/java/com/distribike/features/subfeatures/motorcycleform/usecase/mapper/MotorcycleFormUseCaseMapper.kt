package com.distribike.features.subfeatures.motorcycleform.usecase.mapper

import com.distribike.features.subfeatures.motorcycleform.entity.model.MotorcycleEntityModel
import com.distribike.features.subfeatures.motorcycleform.model.MotorcycleFormModelUi
import com.distribike.features.subfeatures.motorcycleform.repository.model.ClientsRepositoryModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.ClientUseCaseModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.ClientsUseCaseModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleUseCaseModel
import javax.inject.Inject

class MotorcycleFormUseCaseMapper @Inject constructor() {

    fun mapToUseCaseModel(repoModel: ClientsRepositoryModel) = ClientsUseCaseModel(
        clients = repoModel.clients.map { client ->
            ClientUseCaseModel(
                code = client.code,
                name = client.name
            )
        }
    )

    fun mapToClientName(code: String, model: ClientsRepositoryModel): String? {
        val client = model.clients.find { it.code == code }
        return client?.name
    }

    fun mapToFormEntityModel(model: MotorcycleUseCaseModel): MotorcycleEntityModel {
        return MotorcycleEntityModel(
            username = model.username,
            codePrep = model.codePrep,
            model = model.model,
            chassis = model.chassis,
            concessionName = model.concessionName,
            concessionCode = model.concessionCode,
            positionNumber = model.positionNumber,
            startDate = model.startDate
        )
    }

    fun mapToFormUseCaseModel(modelUi: MotorcycleFormModelUi): MotorcycleUseCaseModel {
        return MotorcycleUseCaseModel(
            username = modelUi.username,
            codePrep = modelUi.codePrep,
            model = modelUi.model,
            chassis = modelUi.chassis,
            concessionName = modelUi.concessionName,
            concessionCode = modelUi.concessionCode,
            positionNumber = modelUi.positionNumber,
            startDate = modelUi.startDate
        )
    }
}
