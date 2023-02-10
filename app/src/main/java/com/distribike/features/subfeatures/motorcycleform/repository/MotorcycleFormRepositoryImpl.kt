package com.distribike.features.subfeatures.motorcycleform.repository

import android.content.Context
import com.distribike.features.subfeatures.motorcycleform.repository.model.ClientRepositoryModel
import com.distribike.features.subfeatures.motorcycleform.repository.model.ClientsRepositoryModel
import com.distribike.modules.DispatchersName
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Named

/**
 * Repository that handles interactions with the tasks json file.
 */
class MotorcycleFormRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named(DispatchersName.DATA) private val dispatcher: CoroutineDispatcher
) : MotorcycleFormRepository {

    /**
     * Retrieve a list of tasks to display.
     */
    override suspend fun getClients(): ClientsRepositoryModel =
        withContext(dispatcher) {
            val clients = mutableListOf<ClientRepositoryModel>()
            val inputStream = context.assets.open("clients.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val clientsJson = jsonObject.getJSONArray("clients")
            for (client in 0 until clientsJson.length()) {
                val clientJson = clientsJson.getJSONObject(client)
                clients.add(
                    ClientRepositoryModel(
                        code = clientJson.getString("NCUSTPROD"),
                        name = clientJson.getString("XCUST")
                    )
                )
            }

            ClientsRepositoryModel(clients = clients)
        }
}

