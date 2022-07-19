package com.example.weatherapp.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherQuickPreviewWidget() {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(10) {
            WeatherQuickPreviewCard()
        }
    }

}


@Composable
fun WeatherQuickPreviewCard() {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .wrapContentSize()
            .padding(8.dp),
        color = colorResource(id = R.color.grey_cards),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(

                ) {
                    Text(
                        text = "22",
                        fontSize = 32.sp,
                        color = colorResource(id = R.color.white_text)
                    )
                    Text(
                        text = "Austin",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.white_text)
                    )
                    Text(
                        text = "USA",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.blue_text_more)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(top = 12.dp),
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Weather image"
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "17 %")
                Text(text = "7km/hr")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherQuickPreviewPreview() {
    WeatherQuickPreviewCard()
}

@Preview
@Composable
fun WeatherQuickPreviewWidgetPreview() {
    WeatherQuickPreviewWidget()
}