package com.example.weatherapp.baseui.widget

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R

@Composable
fun DimTintedIcon(
    modifier: Modifier = Modifier,
    tintColour: Color = colorResource(id = R.color.dim_text),
    @DrawableRes drawableRes: Int,
    contentDescription: String,
) {
    Icon(
        modifier = modifier,
        tint = tintColour,
        painter = painterResource(id = drawableRes),
        contentDescription = contentDescription
    )
}