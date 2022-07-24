package com.example.weatherapp.feature.favouritelocations.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherapp.feature.favouritelocations.model.LocationDetail

interface LocationSearchScope : LocationSearchDesignScope {
    val locationDetails: List<LocationDetail>
}

interface LocationSearchDesignScope {
    var boxWidthPercentage: Float
    var shouldWrapContentHeight: Boolean
    var boxMaxHeight: Dp
    var boxBorderStroke: BorderStroke
    var boxShape: Shape
}

class LocationSearchState : LocationSearchScope {

    override val locationDetails: MutableList<LocationDetail> = mutableListOf()

    override var boxWidthPercentage by mutableStateOf(.9f)
    override var shouldWrapContentHeight by mutableStateOf(false)
    override var boxMaxHeight: Dp by mutableStateOf(TextFieldDefaults.MinHeight * 3)
    override var boxBorderStroke by mutableStateOf(BorderStroke(2.dp, Color.Black))
    override var boxShape: Shape by mutableStateOf(RoundedCornerShape(8.dp))
}