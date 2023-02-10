package com.distribike.features.subfeatures.motorcycleform.usecase.mapper

import com.distribike.features.subfeatures.motorcycleform.repository.model.ClientsRepositoryModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.ClientUseCaseModel
import com.distribike.features.subfeatures.motorcycleform.usecase.model.ClientsUseCaseModel
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

    fun mapToClientName(code: String, model: ClientsRepositoryModel) : String? {
        val client = model.clients.find { it.code == code }
        return client?.name
    }
}
