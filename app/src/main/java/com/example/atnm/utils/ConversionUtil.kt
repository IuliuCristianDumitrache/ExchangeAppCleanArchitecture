package com.example.atnm.utils

import com.example.atnm.extensions.roundDouble
import com.example.atnm.models.Rates


object ConversionUtil {
    private lateinit var visited: HashSet<String>
    private lateinit var exchangeRates: ArrayList<Rates>
    var tempFrom: String? = null

    private const val DEFAULT_EXCHANGE_RATE = 1.0
    private const val NO_EXCHANGE_RATE = 0.0

    fun setRates(exchangeRates: ArrayList<Rates>) {
        this.exchangeRates = exchangeRates
    }

    fun getExchangeRate(from: String, to: String): Double {
        return if (from != to) {
            visited = HashSet()
            findExchangeRate(from, to, DEFAULT_EXCHANGE_RATE)
        } else {
            DEFAULT_EXCHANGE_RATE
        }
    }

    private fun findExchangeRate(from: String, to: String, value: Double): Double {
        val resultRate = findExchangeRateBetween(from, to)
        if (resultRate != NO_EXCHANGE_RATE) {
            return (value * resultRate).roundDouble(2)
        }
        visited.add(from)
        exchangeRates.forEach { exchange ->
            if (exchange.from == from && !visited.contains(to)) {
                if (exchange.to != tempFrom) {
                    tempFrom = exchange.from
                    val result =
                        findExchangeRate(exchange.to, to, (value * exchange.rate).roundDouble(2))
                    if (result != NO_EXCHANGE_RATE) {
                        return result
                    }
                }
            }
        }
        return DEFAULT_EXCHANGE_RATE
    }

    private fun findExchangeRateBetween(from: String, to: String): Double {
        exchangeRates.forEach { exchange ->
            if (exchange.from == from && exchange.to == to) {
                return (exchange.rate).roundDouble(2)
            }
        }
        return NO_EXCHANGE_RATE
    }
}