package org.andtho.kotlin.web.restkotlin.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class NumberExtensionTest{

    @Test
    fun createConnection() {
        assertEquals("THOMAS", "thomas".uppercase())
    }

    @Test
    fun `extension functions on BigDecimal`() {
        val result = BigDecimal.valueOf(10)
                .minus(6)
                .double()
                .double()
                .minus(3)
                .round(1)
        println(result)

    }
}