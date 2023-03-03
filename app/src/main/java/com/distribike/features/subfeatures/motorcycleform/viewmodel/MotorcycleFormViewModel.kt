package com.distribike.features.subfeatures.motorcycleform.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distribike.features.subfeatures.motorcycleform.usecase.MotorcycleFormUseCase
import com.distribike.modules.DispatchersName
import com.distribike.utils.asLiveData
import com.distribike.utils.offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MotorcycleFormViewModel @Inject constructor(
    private val useCase: MotorcycleFormUseCase,
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _scanState = MutableLiveData(false)
    val scanState = _scanState.asLiveData()

    private val _validateState = MutableLiveData(false)
    val validateState = _validateState.asLiveData()

    private val _concessionState = MutableStateFlow("")
    val concessionState = _concessionState.asStateFlow()

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

    fun onValidateClicked() {
        viewModelScope.launch(dispatcher) {
            _validateState.offer(true)
        }
    }

    fun onScanClicked() {
        viewModelScope.launch(dispatcher) {
            _scanState.offer(true)
        }
    }

}
