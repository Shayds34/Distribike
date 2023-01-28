package com.distribike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.distribike.features.login.LoginScreen
import com.distribike.ui.theme.DistribikeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistribikeTheme {
                LoginScreen()
            }
        }
    }
}
