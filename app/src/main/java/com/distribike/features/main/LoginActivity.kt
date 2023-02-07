package com.distribike.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.distribike.features.main.viewmodel.LoginViewModel
import com.distribike.features.subfeatures.login.LoginScreen
import com.distribike.features.subfeatures.motorcycleform.MotorcycleFormActivity
import com.distribike.ui.theme.DistribikeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistribikeTheme {
                LoginScreen()
            }
        }

        viewModel.validateState.observe(this) { isClicked ->
            if (isClicked) {
                startActivity(MotorcycleFormActivity.newInstance(this))
            }
        }
    }
}
