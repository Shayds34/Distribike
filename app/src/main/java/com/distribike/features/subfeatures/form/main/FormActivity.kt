package com.distribike.features.subfeatures.form.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.distribike.features.subfeatures.form.main.component.Step
import com.distribike.features.subfeatures.form.main.component.Stepper
import com.distribike.features.subfeatures.form.main.viewmodel.FormViewModel
import com.distribike.features.subfeatures.login.WorkerLottie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, FormActivity::class.java)
    }

    private val viewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewState.observe(this) { data ->
            setContent {
                Column {
                    Spacer(modifier = Modifier.padding(20.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        text = data.sections[0].title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        fontSize = 44.sp
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    Stepper(
                        steps = List(
                            data.sections[0].tasks.size
                        ) { index ->
                            Step(
                                title = data.sections[0].tasks[index].title
                            ) {
                                Row {
                                    WorkerLottie(
                                        modifier = Modifier
                                            .size(200.dp)
                                            .padding(horizontal = 1.dp)
                                    )
                                }
                            }
                        })
                }

            }
        }
    }
}