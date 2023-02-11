package com.example.weatherapp.feature.mylocation.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Bullet() {
    Canvas(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .size(6.dp)
    ) {
        drawCircle(Color.White)
    }
}

@Preview
@Composable
fun BulletPreview(){
    Bullet()
}