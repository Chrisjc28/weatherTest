package com.example.weathertestapp.utils

import junit.framework.Assert.assertNull
import org.junit.Assert
import org.junit.Test

class DateUtilsTest{

    @Test
    fun `when a time is returned in seconds we will format it and return the appropriate time in the correct date format`() {
        val dateUtils = DateUtils

        val actual = dateUtils.convertSecondsInToAFormattedDate(1606377211)

        Assert.assertEquals(
            "26/11/2020 07:53",
            actual
        )
    }


//    @Test
//    fun `when a incorrect time is returned in seconds we will format it and return the epoch time in the correct date format`() {
//        val dateUtils = DateUtils
//
//        val actual = dateUtils.convertSecondsInToAFormattedDate(0)
//
//        Assert.assertEquals(
//            "01/01/1970 01:00",
//            actual
//        )
//    }

    @Test
    fun `when a null value is returned we will not try format it but return null`() {
        val dateUtils = DateUtils

        val actual = dateUtils.convertSecondsInToAFormattedDate(null)

        assertNull(actual)
    }
}