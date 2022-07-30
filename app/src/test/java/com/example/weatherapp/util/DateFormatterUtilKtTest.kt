package com.example.weatherapp.util

import org.junit.Assert
import org.junit.Test

internal class DateFormatterUtilKtTest {

    @Test
    fun `GIVEN ante meridiem time is given, WHEN toTimeInText called, THEN return hour with AM format`() {
        //GIVEN
        val dateInString = "2022-07-28 09:00:00"

        //WHEN
        val formattedTime = dateInString.toTimeInText()

        //THEN
        Assert.assertEquals(
            "09 AM",
            formattedTime
        )
    }

    @Test
    fun `GIVEN post meridiem time is given, WHEN toTimeInText called, THEN return hour with AM format`() {
        //GIVEN
        val dateInString = "2022-07-28 13:00:00"

        //WHEN
        val formattedTime = dateInString.toTimeInText()

        //THEN
        Assert.assertEquals(
            "01 PM",
            formattedTime
        )
    }

    @Test
    fun `WHEN time stamp is given, WHEN getWeekDayName called, THEN return the week day name`() {
        //GIVEN
        val dateInString = "2022-07-26 09:00:00"

        //WHEN
        val formattedTime = dateInString.toWeekDayName()

        //THEN
        Assert.assertEquals(
            "Tuesday",
            formattedTime
        )
    }

    @Test
    fun `WHEN unix time is given, WHEN unixToDate is called, THEN return the time in hours`() {
        //GIVEN
        val time: Long = 1659144636

        //WHEN
        val formattedTime = time.toHoursAndMinutes()

        //THEN
        Assert.assertEquals(
            "07:00 AM",
            formattedTime
        )
    }
}
