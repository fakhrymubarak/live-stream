package com.fakhry.livestream.utils

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class CanonicalRequestTest {

//    @Test
//    fun `generateHeaders defaultValue returnTrue`() {
//        val expected = ""
//    }

    @Test
    fun `generateCanonicalHeaders defaultValue returnTrue`() {
        // arrange
        val headers =
            mapOf("Content-Type" to "application/json; charset=utf-8", "Content-Length" to "46")

        // act
        val actual = generateCanonicalHeaders(headers)

        // assert
        val expected = "content-type:application/json; charset=utf-8" + '\n' + "content-length:46"
        assertEquals(actual, expected)
    }
}