package com.distribike.features.subfeatures.form.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.distribike.features.subfeatures.form.main.viewmodel.FormViewModel
import com.distribike.ui.theme.RedDark
import com.xsims.stepper_compose.Step
import com.xsims.stepper_compose.Stepper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, FormActivity::class.java)
    }

    private val viewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewState.observe(this, Observer { data ->
            setContent {
                Column {
                    Spacer(modifier = Modifier.padding(12.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        text = data.sections[0].title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        fontSize = 38.sp
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    Stepper(
                        steps = List(
                            data.sections[0].tasks.size
                        ) { index ->
                            Step(
                                title = data.sections[0].tasks[index].title,
                                subtitle = data.sections[0].tasks[index].title,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(250.dp)
                                        .fillMaxWidth()
                                        .background(color = RedDark)
                                )
                            }
                        })
                }

            }
        })
    }
}