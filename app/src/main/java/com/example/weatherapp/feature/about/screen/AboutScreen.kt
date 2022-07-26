package com.example.weatherapp.feature.about.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WhiteDim
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.DimText

@Composable
fun AboutScreen(
    onGoClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailsItem(
                header = "Weather Source",
                content = "https://openweathermap.org/",
                onGoClicked = onGoClicked
            )
            Spacer(modifier = Modifier.size(24.dp))
            DetailsItem(
                header = "Github repository",
                content = "https://github.com/codeKrishnan/weather-app",
                onGoClicked = onGoClicked,
            )
        }
    }
}

@Composable
fun DetailsItem(
    header: String,
    content: String,
    onGoClicked: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BrightText(text = header)
        Spacer(modifier = Modifier.size(12.dp))
        Row(
            modifier = Modifier
                .clickable {
                    onGoClicked(content)
                },
        ) {
            DimText(
                text = content,
            )
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .padding(
                        start = 4.dp,
                        top = 8.dp
                    ),
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Go",
                tint = WhiteDim
            )
        }
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    AboutScreen {}
}