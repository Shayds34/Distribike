package com.distribike.features.form.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.distribike.R
import com.distribike.features.form.viewmodel.CameraViewModel

@Composable
fun BarcodeConfirmationDialog(
    negativeButtonColor: Color = Color(0xFFFF0000),
    positiveButtonColor: Color = Color(0xFF1C882A),
    spaceBetweenElements: Dp = 18.dp,
    context: Context = LocalContext.current.applicationContext,
    viewModel: CameraViewModel = hiltViewModel()
) {

    val dialogOpen = viewModel.dialogState.collectAsState()

    if (dialogOpen.value.isNotEmpty()) {
        Dialog(onDismissRequest = {
            viewModel.showDialog("")
        }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(0.92f),
                color = Color.Transparent // dialog background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    // text and buttons
                    Column(
                        modifier = Modifier
                            .padding(top = 30.dp) // this is the empty space at the top
                            .fillMaxWidth()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(percent = 10)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(height = 36.dp))

                        Text(
                            text = "Confirmer ?",
                            fontSize = 24.sp
                        )

                        Spacer(modifier = Modifier.height(height = spaceBetweenElements))

                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Est-ce le bon code:\n${dialogOpen.value}",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(height = spaceBetweenElements))

                        // buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            DialogButton(
                                buttonColor = negativeButtonColor,
                                buttonText = "Annuler"
                            ) {
                                viewModel.onConfirmationClicked(dialogOpen.value)
                            }
                            DialogButton(
                                buttonColor = positiveButtonColor,
                                buttonText = "Confirmer"
                            ) {
                                viewModel.onCancelClicked(dialogOpen.value)
                            }
                        }

                        // If you decrease the Surface's width, increase this height
                        Spacer(modifier = Modifier.height(height = spaceBetweenElements * 2))
                    }

                    // delete icon
                    Icon(
                        painter = painterResource(id = R.drawable.login_image),
                        contentDescription = "Delete Icon",
                        tint = positiveButtonColor,
                        modifier = Modifier
                            .background(color = Color.White, shape = CircleShape)
                            .border(width = 2.dp, shape = CircleShape, color = positiveButtonColor)
                            .padding(all = 16.dp)
                            .align(alignment = Alignment.TopCenter)
                    )
                }
            }
        }
    }
}

@Composable
fun DialogButton(
    cornerRadiusPercent: Int = 26,
    buttonColor: Color,
    buttonText: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(percent = cornerRadiusPercent)
            )
            .clickable {
                onDismiss()
            }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}