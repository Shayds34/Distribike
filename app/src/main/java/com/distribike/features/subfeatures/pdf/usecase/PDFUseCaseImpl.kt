package com.distribike.features.subfeatures.pdf.usecase

import android.util.Log
import com.distribike.features.subfeatures.form.entity.FormSaverEntity
import com.distribike.features.subfeatures.pdf.usecase.mapper.PDFUseCaseMapper
import com.distribike.features.subfeatures.pdf.usecase.model.PDFUseCaseModel
import com.distribike.modules.DispatchersName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Named

/**
 * UseCase that handles interactions with the tasks json file.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PDFUseCaseImpl @Inject constructor(
    private val mapper: PDFUseCaseMapper,
    private val entity: FormSaverEntity,
    @Named(DispatchersName.DOMAIN) private val dispatcher: CoroutineDispatcher
) : PDFUseCase {

    override val pdfResults: Flow<PDFUseCaseModel> by lazy {
        entity.formRecord.mapLatest { result ->
            Log.e("SEBCHA", "useCaseImpl result $result")
            mapper.mapEntityToUseCaseModel(result)
        }
    }
}
