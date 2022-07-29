package com.example.weatherapp.feature.home.screen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.DimText

@Composable
fun SourceDeclarationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BrightText(
            text = "Source"
        )
        DimText(text = "openweathermap.org")
    }
}

@Preview
@Composable
fun SourceDeclarationBarPreview() {
    SourceDeclarationBar()
}