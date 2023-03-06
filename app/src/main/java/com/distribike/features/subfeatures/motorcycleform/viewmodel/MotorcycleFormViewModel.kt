package com.distribike.features.subfeatures.motorcycleform.viewmodel

import androidx.lifecycle.*
import com.distribike.features.subfeatures.form.scanner.entity.CameraEntity
import com.distribike.features.subfeatures.motorcycleform.entity.MotorcycleEntity
import com.distribike.features.subfeatures.motorcycleform.model.MotorcycleFormModelUi
import com.distribike.features.subfeatures.motorcycleform.usecase.MotorcycleFormUseCase
import com.distribike.features.subfeatures.motorcycleform.usecase.mapper.MotorcycleFormUseCaseMapper
import com.distribike.modules.DispatchersName
import com.distribike.utils.asLiveData
import com.distribike.utils.offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MotorcycleFormViewModel @Inject constructor(
    private val useCase: MotorcycleFormUseCase,
    private val entity: MotorcycleEntity,
    private val cameraEntity: CameraEntity,
    private val mapper: MotorcycleFormUseCaseMapper,
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _scanState = MutableLiveData(false)
    val scanState = _scanState.asLiveData()

    private val _validateState = MutableLiveData(false)
    val validateState = _validateState.asLiveData()

    private val _concessionState = MutableStateFlow("")
    val concessionState = _concessionState.asStateFlow()

    private val userNameFlow: Flow<String> by lazy {
           entity.motorcycleForm.mapLatest { it.username ?: "" }
    }

    val usernameLiveData: LiveData<String> = userNameFlow
        .flowOn(dispatcher)
        .mapLatest { it }
        .distinctUntilChanged()
        .asLiveData(dispatcher)

    private val barcodeFlow: Flow<String> by lazy {
        cameraEntity.cameraBarcode
    }

    val barcodeLiveData: LiveData<String> = barcodeFlow
        .flowOn(dispatcher)
        .mapLatest { it }
        .distinctUntilChanged()
        .asLiveData(dispatcher)


    private val _viewState = MutableLiveData<Unit>()
    val viewState by lazy {
        initialize()
        _viewState.asLiveData()
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher) {
            _viewState.offer(value = Unit)
        }
    }

    fun onConcessionCodeEntered(code: String) {
        viewModelScope.launch(dispatcher) {
            val clientName = useCase.getClientWithCode(code)
            _concessionState.value = clientName ?: ""
        }
    }

    fun onValidateClicked(modelUi: MotorcycleFormModelUi) {
        viewModelScope.launch(dispatcher) {
            useCase.storeMotorcycleData(mapper.mapToFormUseCaseModel(modelUi))
            _validateState.offer(true)
        }
    }

    fun onScanClicked() {
        viewModelScope.launch(dispatcher) {
            _scanState.offer(true)
        }
    }

    fun clearChassis() {
        viewModelScope.launch(dispatcher) {
            cameraEntity.clear()
        }
    }

}
