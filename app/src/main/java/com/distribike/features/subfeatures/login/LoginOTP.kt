package com.distribike.features.subfeatures.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.distribike.ui.theme.DistribikeTheme

@Composable
fun LoginUser() {
    var userValue by remember {
        mutableStateOf("")
    }

    TextField(
        value = userValue,
        onValueChange = {
            userValue = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun LoginPassword() {
    var passwordValue by remember {
        mutableStateOf("")
    }

    BasicTextField(
        value = passwordValue,
        onValueChange = {
            if (it.length < 5)
                passwordValue = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(4) { index ->
                    val char = when {
                        index >= passwordValue.length -> ""
                        else -> passwordValue[index].toString()
                    }
                    val isFocused = passwordValue.length == index
                    Text(
                        modifier = Modifier
                            .width(40.dp)
                            .border(
                                if (isFocused) 2.dp
                                else 1.dp,
                                Color.LightGray,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(2.dp),
                        text = char,
                        style = MaterialTheme.typography.h4,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )

                    if (index != passwordValue.length) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DistribikeTheme {
        LoginUser()
        LoginPassword()
    }
}