package com.example.weatherapp.baseui.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun DimText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = colorResource(id = R.color.dim_text)
    )
}

@Composable
fun BrightText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = colorResource(id = R.color.white_text)
    )
}

@Composable
fun BrightTextLarge(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 32.sp,
        color = colorResource(id = R.color.white_text)
    )
}