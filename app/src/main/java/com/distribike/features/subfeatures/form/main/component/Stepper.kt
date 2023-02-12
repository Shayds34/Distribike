package com.distribike.features.subfeatures.form.main.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    //type: StepperType = StepperType.vertical,
    scrollState: ScrollState = rememberScrollState(1),
    currentStep: MutableState<Int> = remember { mutableStateOf(0) },
    nextButton: @Composable (() -> Unit)? = {
        Button(
            onClick = {
                if (currentStep.value < steps.size) {
                    steps.getOrNull(currentStep.value)?.state?.value = StepState.COMPLETE
                    currentStep.value = currentStep.value + 1
                }
            }) {
            Text(
                text = "Valider".uppercase(),
                fontSize = 24.sp
            )
        }
    },
    passButton: @Composable (() -> Unit)? = {
        Button(onClick = {
            if (currentStep.value < steps.size) {
                steps.getOrNull(currentStep.value)?.state?.value = StepState.PASS
                currentStep.value = currentStep.value + 1
            }
        }) {
            Text(
                "Passer".uppercase(),
                fontSize = 24.sp
            )
        }
    },
    previousButton: @Composable (() -> Unit)? = {
        TextButton(onClick = {
            if (currentStep.value > 0) {
                steps.getOrNull(currentStep.value)?.state?.value = StepState.TODO
                currentStep.value = currentStep.value - 1
            }
        }) {
            Text(
                "Retour".uppercase(),
                fontSize = 24.sp, color = Color.Gray
            )
        }
    },
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
                modifier = Modifier.clickable { currentStep.value = index },
                index = index + 1,
                step = step,
                expanded = index == currentStep.value,
                isLastStep = index == (steps.size - 1),
                nextButton = nextButton,
                passButton = passButton,
                previousButton = previousButton,
            )
        }
    }
}

/**
 * Defines the [Stepper]'s main axis.
 */
enum class StepperType {
    // A vertical layout of the steps with their content in-between the titles.
    vertical,

    // A horizontal layout of the steps with their content below the titles.
    horizontal
}