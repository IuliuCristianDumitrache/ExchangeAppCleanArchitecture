package com.example.atnm.data.repository

import com.example.atnm.models.RatesResponse
import com.example.atnm.network.Resource

interface ExchangeRepository {
    suspend fun fetchExchangeData(): Resource<RatesResponse>
}