package com.distribike.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.distribike.features.subfeatures.login.LoginScreen
import com.distribike.ui.theme.DistribikeTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistribikeTheme {
                LoginScreen()
            }
        }
    }
}
