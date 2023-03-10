package com.distribike.features.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class LoginViewModel @Inject constructor(
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _validateState = MutableLiveData(false)
    val validateState = _validateState.asLiveData()

    private val _viewState = MutableStateFlow(Unit)
    val viewState = _viewState.asStateFlow()

    fun onValidateClicked() {
        viewModelScope.launch(dispatcher) {
            _validateState.offer(true)
        }
    }
}
