package com.example.weatherapp.baseui.widget

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.feature.favouritelocations.model.WeatherType
import com.example.weatherapp.feature.favouritelocations.model.getIcon

@Composable
fun GradientIcon(
    modifier: Modifier = Modifier,
    weatherType: WeatherType,
) {
    val colorGradient = Brush.verticalGradient(
        listOf(Color.Blue, Color.Magenta)
    )

    Icon(
        modifier = Modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(colorGradient, blendMode = BlendMode.SrcAtop)
                }
            }
            .then(modifier),
        painter = painterResource(
            id = weatherType.getIcon()
        ),
        contentDescription = "Weather image",
    )
}