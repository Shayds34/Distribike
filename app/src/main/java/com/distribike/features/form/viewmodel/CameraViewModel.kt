package com.distribike.features.form.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distribike.modules.DispatchersName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CameraViewModel @Inject constructor(
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _dialogState = MutableStateFlow("")
    val dialogState = _dialogState.asStateFlow()

    fun showDialog(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            _dialogState.emit(barcodeValue)
        }
    }

    fun onConfirmationClicked(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            // TODO pass to the previous screen
        }
    }

    fun onCancelClicked(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            // TODO
        }
    }
}