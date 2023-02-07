package com.distribike.features.subfeatures.motorcycleform.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distribike.features.subfeatures.form.main.mapper.FormUiMapper
import com.distribike.features.subfeatures.form.main.model.FormModelUi
import com.distribike.features.subfeatures.form.usecase.FormUseCase
import com.distribike.modules.DispatchersName
import com.distribike.utils.asLiveData
import com.distribike.utils.offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MotorcycleFormViewModel @Inject constructor(
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _validateState = MutableLiveData(false)
    val validateState = _validateState.asLiveData()

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

    fun onValidateClicked() {
        viewModelScope.launch(dispatcher) {
            _validateState.offer(true)

        }
    }

}
