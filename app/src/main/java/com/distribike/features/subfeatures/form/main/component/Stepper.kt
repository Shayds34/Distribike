package com.distribike.features.subfeatures.form.main.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Creates a stepper from a list of steps.
 * This composable function is not meant to be rebuilt with a different list of steps
 * unless a key is provided in order to distinguish the old stepper from the
 * new one.
 *
 * The [steps], [type], and [currentStep] arguments must not be null.
 */
@Composable
fun Stepper(
    scrollState: ScrollState = rememberScrollState(1),
    currentStep: MutableState<Int> = remember { mutableStateOf(0) },
    nextButton: @Composable (() -> Unit)? = null,
    passButton: @Composable (() -> Unit)? = null,
    previousButton: @Composable (() -> Unit)? = null,
    completeFormButton: @Composable (() -> Unit)? = null,
    steps: List<Step>,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .verticalScroll(
                state = scrollState,
                enabled = true,
                reverseScrolling = false,
            )
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        steps.forEachIndexed { index, step ->
            StepUi(
                index = index + 1,
                step = step,
                expanded = index == currentStep.value,
                isLastStep = index == (steps.size - 1),
                nextButton = nextButton,
                passButton = passButton,
                previousButton = previousButton,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        completeFormButton?.invoke()
        Spacer(modifier = Modifier.padding(16.dp))
    }
}
