package com.distribike.features.subfeatures.form.main.forms.poweringform.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.distribike.features.subfeatures.form.main.component.StepState
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
class PoweringFormViewModel @Inject constructor(
    private val useCase: FormUseCase,
    private val mapper: FormUiMapper,
    @Named(DispatchersName.UI_VIEWMODEL) val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val shouldEnableNextButton = useCase.isPoweringFormCompleted.asLiveData()

    private val _viewState = MutableLiveData<FormModelUi>()
    val viewState by lazy {
        initialize()
        _viewState.asLiveData()
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher) {
            val useCaseModel = useCase.getTasks()
            _viewState.offer(
                mapper.mapToModelUi(useCaseModel)
            )
        }
    }

    fun saveCurrentStepState(
        state: StepState,
        currentStep: Int,
        additionalInfo: String? = null,
        additionalInfo2: String? = null
    ) {
        viewModelScope.launch(dispatcher) {
            useCase.savePoweringStepState(
                state = state,
                currentStep = currentStep,
                additionalInfo = additionalInfo,
                additionalInfo2 = additionalInfo2
            )
        }
    }

}
