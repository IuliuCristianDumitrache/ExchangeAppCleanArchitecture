package com.example.atnm.extensions

import kotlin.math.round

fun Double.roundDouble(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}