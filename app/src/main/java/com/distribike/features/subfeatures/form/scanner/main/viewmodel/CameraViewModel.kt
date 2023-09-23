package com.distribike.features.subfeatures.form.scanner.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distribike.features.subfeatures.form.scanner.entity.CameraEntity
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
class CameraViewModel @Inject constructor(
    private val cameraEntity: CameraEntity,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _chassisState = MutableStateFlow("")
    val chassisState = _chassisState.asStateFlow()

    private val _dialogState = MutableStateFlow("")
    val dialogState = _dialogState.asStateFlow()

    private val _viewState = MutableLiveData<CameraModelUi>()
    val viewState = _viewState.asLiveData()

    data class CameraModelUi(
        val state: State = State.Init
    ) {
        sealed class State {
            /**
             * Used to display the heartbeat animation
             * for the camera reticule
             */
            object Init : State()

            /**
             * Used to display the loading animation
             * for the camera reticule
             */
            object Loading : State()

            /**
             * Used to save the validated barcode
             */
            object Success : State()
        }
    }

    fun showDialog(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            _dialogState.emit(barcodeValue)
        }
    }

    fun onConfirmationClicked(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            cameraEntity.saveCameraBarcode(barcodeValue)
            _chassisState.value = barcodeValue
        }
    }

    fun onCancelClicked(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            // TODO
        }
    }

    fun onShowReticuleAnimation() {
        viewModelScope.launch(dispatcher) {
            _viewState.offer(CameraModelUi(state = CameraModelUi.State.Init))
        }
    }

    fun onScannedBarcodeLoadingFinished() {
        viewModelScope.launch(dispatcher) {
            _viewState.offer(CameraModelUi(state = CameraModelUi.State.Success))
        }
    }

    fun handleSuccess() {
        viewModelScope.launch(dispatcher) {
            // Reset to Init state, otherwise it could stay in Success state.
            _viewState.offer(CameraModelUi(state = CameraModelUi.State.Init))
        }
    }

    fun onBarcodeFound(barcodeValue: String) {
        viewModelScope.launch(dispatcher) {
            cameraEntity.saveCameraBarcode(barcodeValue)
            _viewState.offer(CameraModelUi(state = CameraModelUi.State.Loading))
        }
    }
}