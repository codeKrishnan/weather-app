package com.example.weatherapp.feature.favouritelocations.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.BrightText

@Composable
fun ErrorIndicator(
    onRetryClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            painter = painterResource(id = R.drawable.sad),
            contentDescription = "Something went wrong icon",
            tint = colorResource(id = R.color.blue_icon_tint)
        )
        BrightText(
            modifier = Modifier
                .clickable {
                    onRetryClicked()
                },
            text = stringResource(id = R.string.error_message)
        )
    }
}

@Preview
@Composable
private fun ErrorIndicatorPreview() {
    ErrorIndicator(onRetryClicked = {})
}