package com.example.weatherapp.feature.weatherforecast.screen.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.baseui.widget.DimText

@Composable
fun MaxAndMinTemperatureBar(
    day: String,
    minAndMaxTemp: Pair<String, String> = Pair("", ""),
    onClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(day)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BrightText(
            modifier = Modifier
                .width(100.dp),
            text = day
        )
        Row {
            BrightText(text = "${minAndMaxTemp.first}°")
            Spacer(modifier = Modifier.size(12.dp))
            DimText(text = "${minAndMaxTemp.second}°")
        }
    }
}

@Preview
@Composable
private fun MaxAndMinTemperatureBarPreview() {
    MaxAndMinTemperatureBar(
        day = "Monday",
        onClick = {},
        minAndMaxTemp = Pair("21", "25")
    )
}