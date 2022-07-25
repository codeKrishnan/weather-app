package com.example.weatherapp.feature.base.screen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.WeatherAppTheme
import com.example.weatherapp.baseui.widget.DimTintedIcon

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(
                bottom = 8.dp
            )
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        DimTintedIcon(
            modifier = Modifier.size(25.dp),
            drawableRes = R.drawable.home,
            contentDescription = "Home"
        )
        DimTintedIcon(
            modifier = Modifier.size(30.dp),
            drawableRes = R.drawable.list,
            contentDescription = "City list"
        )
        DimTintedIcon(
            modifier = Modifier.size(30.dp),
            drawableRes = R.drawable.info,
            contentDescription = "About"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigationBarPreview() {
    WeatherAppTheme {
        NavigationBar()
    }
}
