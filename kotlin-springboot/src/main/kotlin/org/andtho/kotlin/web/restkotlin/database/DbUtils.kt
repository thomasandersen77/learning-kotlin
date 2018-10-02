package org.andtho.kotlin.web.restkotlin.database

import java.math.BigDecimal
import java.math.RoundingMode


fun String.uppercase() : String {
    return this.toUpperCase()
}

fun BigDecimal.round(points : Int) : BigDecimal = this.setScale(points, RoundingMode.UP)
fun BigDecimal.double() : BigDecimal = this.multiply(BigDecimal.valueOf(2))
fun BigDecimal.minus(value : Int) : BigDecimal = this.subtract(BigDecimal.valueOf(value.toDouble()))