package com.example.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:SS",
        Locale.getDefault()
    )
    return dateFormat.parse(this)
}

fun String.toTimeInText(): String {

    val timeFormat = SimpleDateFormat(
        "hh a",
        Locale.getDefault()
    )
    return timeFormat.format(this.toDate()!!).uppercase()
}

fun String.toWeekDayName(): String {
    val weekDayFormat = SimpleDateFormat(
        "EEEE",
        Locale.getDefault()
    )

    val today = Calendar.getInstance().time

    return if (weekDayFormat.format(today) == weekDayFormat.format(this.toDate()!!)) {
        return "Today"
    } else {
        weekDayFormat.format(this.toDate()!!)
    }
}