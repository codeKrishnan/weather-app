package com.example.weatherapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

val currentDate: Date
    get() = Calendar.getInstance().time

val timeFormatHourAndMinutesWithMeridiem by lazy {
    SimpleDateFormat(
        "hh:mm a",
        Locale.getDefault()
    )
}

val timeFormatHourAndMinutes by lazy {
    SimpleDateFormat(
        "hh:mm",
        Locale.getDefault()
    )
}

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
        "MM EEEE",
        Locale.getDefault()
    )

    return if (weekDayFormat.format(currentDate) == weekDayFormat.format(this.toDate()!!)) {
        return "Today"
    } else {
        weekDayFormat.format(this.toDate()!!)
    }
}

fun getCurrentDate(): String {
    val currentDayFormat = SimpleDateFormat(
        "E, dd MMM",
        Locale.getDefault()
    )

    return currentDayFormat.format(currentDate)
}

fun Long.toDate(): Date = Date().apply { time = this@toDate * 100 }

fun Long?.toHoursAndMinutes(): String {
    if (this == null) return ""
    return timeFormatHourAndMinutesWithMeridiem.format(this.toDate()).uppercase()
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.isBeforeSunrise(): Boolean {
    val timeToCompare = LocalTime.parse(
        timeFormatHourAndMinutes.format(this.toDate())
    )
    val currentTime = timeFormatHourAndMinutes.format(currentDate)

    return timeToCompare.isAfter(LocalTime.parse(currentTime))
}