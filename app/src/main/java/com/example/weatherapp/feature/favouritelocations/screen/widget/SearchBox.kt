package com.example.weatherapp.feature.favouritelocations.screen.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.BrightText
import com.example.weatherapp.feature.favouritelocations.model.LocationDetail
import com.example.weatherapp.feature.favouritelocations.util.LocationSearchDesignScope
import com.example.weatherapp.feature.favouritelocations.util.LocationSearchState

@Composable
fun SearchBox(
    locationSearchState: LocationSearchState,
    onResultSelected: (LocationDetail) -> Unit,
    onQueryChanged: (String) -> Unit,
) {
    val state = remember {
        locationSearchState
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            onQueryChanged = onQueryChanged
        )
        AnimatedVisibility(visible = !state.shouldWrapContentHeight) {
            LazyColumn(
                modifier = Modifier
                    .autoComplete(locationSearchDesignScope = state)
                    .background(color = colorResource(id = R.color.grey_background)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                with(state.locationDetails.value) {
                    items(size) { index ->
                        Box(
                            modifier = Modifier
                                .clickable {
                                    onResultSelected(this@with[index])
                                    state.locationDetails.value = emptyList()
                                }
                                .padding(vertical = 16.dp)
                        ) {
                            BrightText(
                                text = this@with[index].cityName
                            )
                        }
                    }
                }
            }
        }
    }

}

private fun Modifier.autoComplete(
    locationSearchDesignScope: LocationSearchDesignScope,
): Modifier {
    val baseModifier = if (locationSearchDesignScope.shouldWrapContentHeight) {
        wrapContentHeight()
    } else {
        heightIn(0.dp, locationSearchDesignScope.boxMaxHeight)
    }

    return baseModifier
        .fillMaxWidth(locationSearchDesignScope.boxWidthPercentage)
        .border(
            border = locationSearchDesignScope.boxBorderStroke,
            shape = locationSearchDesignScope.boxShape
        )
}


@Composable
@Preview
private fun SearchBoxPreview() {
    SearchBox(
        locationSearchState = LocationSearchState().apply {
            locationDetails = mutableStateOf(listOf(
                LocationDetail(
                    "Kochi", "", ""
                ),
                LocationDetail(
                    "Trivandrum", "", ""
                ),
                LocationDetail(
                    "Koilon", "", ""
                )
            ))
        },
        onResultSelected = {},
        onQueryChanged = {},
    )
}
