package org.andtho.kotlin.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CityTest {

    @Test
    fun pretty() {
        val hokksund = City.HOKKSUND.pretty()
        println(hokksund)
        assertEquals("HOKKSUND", hokksund)
    }
}