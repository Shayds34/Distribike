package com.distribike.features.subfeatures.pdf.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.distribike.features.subfeatures.form.entity.FormSaverEntity
import com.distribike.features.subfeatures.form.entity.models.FormRecordEntityModel
import com.distribike.features.subfeatures.pdf.mapper.PDFMapperUi
import com.distribike.features.subfeatures.pdf.model.*
import com.distribike.features.subfeatures.pdf.usecase.PDFUseCase
import com.distribike.modules.DispatchersName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PDFViewModel @Inject constructor(
    private val useCase: PDFUseCase,
    private val mapper: PDFMapperUi,
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val formRecordLiveData: LiveData<PDFModelUi> = useCase.pdfResults
        .flowOn(dispatcher)
        .mapLatest {
            mapper.mapToModelUi(it)
        }
        .distinctUntilChanged()
        .asLiveData(dispatcher)
}