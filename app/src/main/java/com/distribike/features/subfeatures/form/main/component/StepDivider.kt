package com.distribike.features.subfeatures.form.main.component

import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StepDivider(
    modifier: Modifier,
    color: Color
) {
    Divider(
        color = color,
        modifier = modifier
            .width(1.dp)
    )
}