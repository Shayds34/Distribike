package com.distribike.features.subfeatures.form.main.forms.transmissionform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.distribike.features.subfeatures.form.main.component.Step
import com.distribike.features.subfeatures.form.main.component.StepState
import com.distribike.features.subfeatures.form.main.component.Stepper
import com.distribike.features.subfeatures.form.main.forms.coolingform.CoolingFormActivity
import com.distribike.features.subfeatures.form.main.forms.transmissionform.viewmodel.TransmissionFormViewModel
import com.distribike.features.subfeatures.form.main.model.FormModelUi
import com.distribike.features.subfeatures.login.WorkerLottie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransmissionFormActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, TransmissionFormActivity::class.java)
    }

    private val viewModel: TransmissionFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val data = viewModel.viewState.observeAsState(FormModelUi(sections = listOf()))

            var additionalInfo by remember {
                mutableStateOf("")
            }
            var additionalInfo2 by remember {
                mutableStateOf("")
            }

            Column {
                Spacer(modifier = Modifier.padding(20.dp))

                if (data.value.sections.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        text = data.value.sections[5].title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        fontSize = 44.sp
                    )

                    val currentStep = remember { mutableStateOf(0) }
                    val steps = List(
                        data.value.sections[5].tasks.size
                    ) { index ->
                        Step(
                            title = data.value.sections[5].tasks[index].title
                        ) {
                            Row {
                                WorkerLottie(
                                    modifier = Modifier
                                        .size(200.dp)
                                        .padding(horizontal = 1.dp)
                                )

                                Column {
                                    if (data.value.sections[5].tasks[index].additionalInfo == "NEEDED") {
                                        Row {
                                            Text(
                                                text = data.value.sections[5].tasks[index].description,
                                                modifier = Modifier
                                                    .wrapContentWidth()
                                                    .padding(5.dp)
                                            )
                                            TextField(
                                                value = additionalInfo,
                                                onValueChange = { additionalInfo = it }
                                            )
                                        }
                                    }
                                    if (data.value.sections[5].tasks[index].additionalInfo2 == "NEEDED") {
                                        Row {
                                            Text(
                                                text = data.value.sections[5].tasks[index].description,
                                                modifier = Modifier
                                                    .wrapContentWidth()
                                                    .padding(5.dp)
                                            )
                                            TextField(
                                                value = additionalInfo2,
                                                onValueChange = { additionalInfo2 = it }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    Stepper(
                        currentStep = currentStep,
                        nextButton = {
                            Button(
                                enabled = if (currentStep.value < data.value.sections[5].tasks.size) {
                                    if (data.value.sections[5].tasks[currentStep.value].additionalInfo == "NEEDED" ||
                                        data.value.sections[5].tasks[currentStep.value].additionalInfo2 == "NEEDED"
                                    ) {
                                        additionalInfo.isNotEmpty() && additionalInfo2.isNotEmpty()
                                    } else {
                                        true
                                    }
                                } else {
                                    true
                                },
                                onClick = {
                                    viewModel.saveCurrentStepState(
                                        state = StepState.COMPLETE,
                                        currentStep = currentStep.value,
                                        additionalInfo = additionalInfo.ifEmpty { null },
                                        additionalInfo2 = additionalInfo2.ifEmpty { null }
                                    )
                                    additionalInfo = ""
                                    additionalInfo2 = ""
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
                                    additionalInfo = ""
                                    additionalInfo2 = ""
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
                                    additionalInfo = ""
                                    additionalInfo2 = ""
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
                                    startActivity(CoolingFormActivity.newInstance(context = applicationContext))
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