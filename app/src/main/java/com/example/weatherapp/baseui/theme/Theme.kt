package com.example.weatherapp.baseui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color(color = R.color.grey_background),
    primaryVariant = Purple700,
    secondary = Color(color = R.color.grey_cards),
    background = Color(color = R.color.grey_background),
    surface = Color(color = R.color.grey_background),
    onPrimary = Color(color = R.color.white_text),
    onSecondary = Color(color = R.color.dim_text),
    onBackground = Color(color = R.color.white_text),
    onSurface = Color(color = R.color.dim_text),
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Color(color = R.color.grey_background),
    primaryVariant = Purple700,
    secondary = Color(color = R.color.grey_cards),
    background = Color(color = R.color.grey_background),
    surface = Color(color = R.color.grey_background),
    onPrimary = Color(color = R.color.white_text),
    onSecondary = Color(color = R.color.dim_text),
    onBackground = Color(color = R.color.white_text),
    onSurface = Color(color = R.color.dim_text),
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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