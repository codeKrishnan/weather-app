package com.example.weatherapp.baseui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun BlueCallOut(
    label: String,
) {
    BrightText(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.violet_pill_background),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        text = label,
    )
}