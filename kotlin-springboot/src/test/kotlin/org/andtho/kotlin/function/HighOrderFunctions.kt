package org.andtho.kotlin.function

import org.junit.Test
import kotlin.test.assertEquals

class HighOrderFunctions {

    fun multiply(number : Int, roundFun : (Double) -> Int) : Int {
        return number * roundFun(number.toDouble())
    }

    fun round(number : Double) : (Double) -> Int = {
        number.toInt()
    }

    @Test
    fun `multilply using round function`() {
        val res = multiply(10, round(10.0))
        assertEquals(res, 100)
    }


}