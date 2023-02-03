package com.distribike.features.main.viewmodel

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
class LoginViewModel @Inject constructor(
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow(Unit)
    val viewState = _viewState.asStateFlow()

    fun onViewResumed() {
        viewModelScope.launch(dispatcher) {
            // TODO
        }
    }

}