package com.example.atnm.domainlayer

import com.example.atnm.data.repository.ExchangeRepository
import com.example.atnm.network.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchExchangeUseCase @Inject constructor(private val repository: ExchangeRepository) {
    suspend fun fetchExchangeData() =
        flow {
            emit(Resource.Loading())
            val response = repository.fetchExchangeData()
            emit(response)
        }
}