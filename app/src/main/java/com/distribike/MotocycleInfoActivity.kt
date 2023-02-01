package com.distribike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.distribike.features.MotocycleInfo.Motocycleform
import com.distribike.ui.theme.DistribikeTheme

class MotocycleInfoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistribikeTheme {
                Motocycleform()
            }
        }
    }
}
