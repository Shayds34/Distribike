package com.distribike.features.subfeatures.form.main.forms.steeringform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.distribike.features.subfeatures.form.main.component.Step
import com.distribike.features.subfeatures.form.main.component.StepState
import com.distribike.features.subfeatures.form.main.component.Stepper
import com.distribike.features.subfeatures.form.main.forms.othersform.viewmodel.OthersFormViewModel
import com.distribike.features.subfeatures.form.main.forms.steeringform.viewmodel.SteeringFormViewModel
import com.distribike.features.subfeatures.form.main.model.FormModelUi
import com.distribike.features.subfeatures.login.WorkerLottie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SteeringFormActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, SteeringFormActivity::class.java)
    }

    private val viewModel: SteeringFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val data = viewModel.viewState.observeAsState(FormModelUi(sections = listOf()))

            Column {
                Spacer(modifier = Modifier.padding(20.dp))

                if (data.value.sections.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        text = data.value.sections[12].title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        fontSize = 44.sp
                    )

                    val currentStep = remember { mutableStateOf(0) }
                    val steps = List(
                        data.value.sections[12].tasks.size
                    ) { index ->
                        Step(
                            title = data.value.sections[12].tasks[index].title
                        ) {
                            Row {
                                WorkerLottie(
                                    modifier = Modifier
                                        .size(200.dp)
                                        .padding(horizontal = 1.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    Stepper(
                        currentStep = currentStep,
                        nextButton = {
                            Button(
                                onClick = {
                                    viewModel.saveCurrentStepState(
                                        state = StepState.COMPLETE,
                                        currentStep = currentStep.value
                                    )
                                    if (currentStep.value < steps.size) {
                                        steps.getOrNull(currentStep.value)?.state?.value =
                                            StepState.COMPLETE
                                        currentStep.value = currentStep.value + 1
                                    }
                                }) {
                                Text(
                                    text = "Valider".uppercase(),
                                    fontSize = 24.sp
                                )
                            }
                        },
                        passButton = {
                            Button(
                                onClick = {
                                    viewModel.saveCurrentStepState(
                                        state = StepState.PASS,
                                        currentStep = currentStep.value
                                    )
                                    if (currentStep.value < steps.size) {
                                        steps.getOrNull(currentStep.value)?.state?.value =
                                            StepState.PASS
                                        currentStep.value = currentStep.value + 1
                                    }
                                }) {
                                Text(
                                    text = "Passer".uppercase(),
                                    fontSize = 24.sp
                                )
                            }
                        },
                        previousButton = {
                            Button(
                                onClick = {
                                    viewModel.saveCurrentStepState(
                                        state = StepState.TODO,
                                        currentStep = currentStep.value
                                    )
                                    if (currentStep.value > 0) {
                                        steps.getOrNull(currentStep.value)?.state?.value =
                                            StepState.TODO
                                        currentStep.value = currentStep.value - 1
                                    }
                                }) {
                                Text(
                                    text = "Retour".uppercase(),
                                    fontSize = 24.sp
                                )
                            }
                        },
                        steps = steps,
                        completeFormButton = {
                            Button(
                                enabled = viewModel.shouldEnableNextButton.observeAsState(false).value,
                                onClick = {
                                    finish()
                                    startActivity(SteeringFormActivity.newInstance(context = applicationContext))
                                }) {
                                Text(
                                    text = "Section suivante".uppercase(),
                                    fontSize = 24.sp
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}