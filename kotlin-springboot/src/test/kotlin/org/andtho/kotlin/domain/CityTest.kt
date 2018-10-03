package org.andtho.kotlin.domain

import org.junit.Test

import org.junit.Assert.*

class CityTest {

    @Test
    fun pretty() {
        val hokksund = City.HOKKSUND.pretty()
        println(hokksund)
        assertEquals("Hokksund", hokksund)
    }
}