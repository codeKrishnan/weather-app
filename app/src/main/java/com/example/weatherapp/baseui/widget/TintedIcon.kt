package com.example.weatherapp.baseui.widget

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.baseui.theme.WhiteDim

@Composable
fun DimTintedIcon(
    modifier: Modifier = Modifier,
    tintColour: Color = WhiteDim,
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