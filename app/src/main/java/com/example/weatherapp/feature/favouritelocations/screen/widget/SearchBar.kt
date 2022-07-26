package com.example.weatherapp.feature.favouritelocations.screen.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.theme.BlueTint
import com.example.weatherapp.baseui.theme.GreyBackground
import com.example.weatherapp.baseui.theme.WhiteBright
import com.example.weatherapp.baseui.theme.WhiteDim

@Composable
fun SearchBar(
    onQueryChanged: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            modifier = Modifier
                .padding(16.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search Icon",
            tint = BlueTint,
        )

        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            singleLine = true,
            label = {
                Text(
                    text = "Search",
                    color = WhiteDim
                )
            },
            onValueChange = { query ->
                text = query
                onQueryChanged(query)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = { }),
            colors = TextFieldDefaults.textFieldColors(
                textColor = WhiteBright,
                disabledTextColor = Color.Transparent,
                backgroundColor = GreyBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        onQueryChanged = {}
    )
}