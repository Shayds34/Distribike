package com.distribike.features.subfeatures.motorcycleform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.distribike.features.subfeatures.form.main.forms.generalform.GeneralFormActivity
import com.distribike.features.subfeatures.form.main.forms.wheelsform.WheelsFormActivity
import com.distribike.features.subfeatures.form.scanner.main.CameraActivity
import com.distribike.features.subfeatures.motorcycleform.viewmodel.MotorcycleFormViewModel
import com.distribike.features.subfeatures.pdf.PDFActivity
import com.distribike.ui.theme.DistribikeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MotorcycleFormActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, MotorcycleFormActivity::class.java)
    }

    private val viewModel: MotorcycleFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistribikeTheme {
                MotorcycleForm()
            }
        }

        viewModel.scanState.observe(this) { isClicked ->
            if (isClicked) {
                startActivity(CameraActivity.newInstance(this))
            }
        }

        viewModel.validateState.observe(this) { isClicked ->
            if (isClicked) {
                startActivity(GeneralFormActivity.newInstance(this))
            }
        }
    }
}
