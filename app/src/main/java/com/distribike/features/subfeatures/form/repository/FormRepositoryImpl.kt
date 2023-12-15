package com.distribike.features.subfeatures.form.repository

import android.content.Context
import com.distribike.features.subfeatures.form.repository.model.Section
import com.distribike.features.subfeatures.form.repository.model.SectionsRepositoryModel
import com.distribike.features.subfeatures.form.repository.model.Task
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
class FormRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named(DispatchersName.DATA) private val dispatcher: CoroutineDispatcher
) : FormRepository {

    /**
     * Retrieve a list of tasks to display.
     */
    override suspend fun getTasks(): SectionsRepositoryModel =
        withContext(dispatcher) {
            val sections = mutableListOf<Section>()
            val inputStream = context.assets.open("tasks.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val sectionsJson = jsonObject.getJSONArray("sections")
            for (section in 0 until sectionsJson.length()) {
                val sectionJson = sectionsJson.getJSONObject(section)
                val tasksJson = sectionJson.getJSONArray("tasks")
                val tasks = mutableListOf<Task>()
                for (task in 0 until tasksJson.length()) {
                    val taskJson = tasksJson.getJSONObject(task)
                    tasks.add(
                        Task(
                            id = taskJson.getInt("id"),
                            title = taskJson.getString("title"),
                            description = taskJson.getString("description"),
                            additionalInfo = taskJson.getNullableString("additional_info"),
                            additionalInfo2 = taskJson.getNullableString("additional_info_2")
                        )
                    )
                }
                sections.add(
                    Section(
                        title = sectionJson.getString("title"),
                        tasks = tasks
                    )
                )
            }

            SectionsRepositoryModel(sections = sections)
        }

    private fun JSONObject.getNullableString(name: String): String? {
        if (has(name) && !isNull(name)) {
            return getString(name)
        }
        return null
    }

}
