package com.distribike.features.subfeatures.pdf.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.distribike.features.subfeatures.form.entity.FormSaverEntity
import com.distribike.features.subfeatures.form.entity.models.FormRecordEntityModel
import com.distribike.features.subfeatures.pdf.mapper.PDFMapperUi
import com.distribike.features.subfeatures.pdf.model.*
import com.distribike.features.subfeatures.pdf.usecase.PDFUseCase
import com.distribike.modules.DispatchersName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PDFViewModel @Inject constructor(
    private val useCase: PDFUseCase,
    private val entity: FormSaverEntity,
    private val mapper: PDFMapperUi,
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val formRecordLiveData: LiveData<FormRecordEntityModel> = entity.formRecord.asLiveData()
}
