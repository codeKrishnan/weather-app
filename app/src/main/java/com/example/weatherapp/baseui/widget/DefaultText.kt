package com.example.weatherapp.baseui.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.weatherapp.baseui.theme.WhiteBright
import com.example.weatherapp.baseui.theme.WhiteDim

@Composable
fun DimText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        color = WhiteDim
    )
}

@Composable
fun BrightText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        color = WhiteBright,
        textAlign = textAlign
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
        color = WhiteBright
    )
}