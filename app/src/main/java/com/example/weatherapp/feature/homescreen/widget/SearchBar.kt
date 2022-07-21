package com.example.weatherapp.feature.homescreen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        var text by remember { mutableStateOf("") }
        Icon(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search Icon",
            tint = colorResource(id = R.color.blue_icon_tint)
        )
        TextField(
            value = text,
            singleLine = true,
            label = {
                Text(
                    text = "Search",
                    color = colorResource(id = R.color.dim_text)
                )
            },
            onValueChange = { text = it },

            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = R.color.white_text),
                disabledTextColor = Color.Transparent,
                backgroundColor = colorResource(id = R.color.grey_background),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Icon(
            imageVector = Icons.Outlined.Edit,
            contentDescription = "Edit icon",
            tint = colorResource(id = R.color.blue_icon_tint),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar()
}