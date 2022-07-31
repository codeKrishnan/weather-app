package com.example.weatherapp.baseui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = GreyBackground,
    primaryVariant = GreyCardBackground,
    secondary = GreyCardBackground,
    background = GreyBackground,
    surface = GreyCardBackground,
    onPrimary = WhiteBright,
    onSecondary = WhiteDim,
    onBackground = WhiteBright,
    onSurface = WhiteDim,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = GreyBackground,
    primaryVariant = GreyCardBackground,
    secondary = GreyCardBackground,
    background = GreyBackground,
    surface = GreyCardBackground,
    onPrimary = WhiteBright,
    onSecondary = WhiteDim,
    onBackground = WhiteBright,
    onSurface = WhiteDim,
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}