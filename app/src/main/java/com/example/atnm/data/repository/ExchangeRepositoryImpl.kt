package com.example.atnm.data.repository

import com.example.atnm.data.remotedatasource.ExchangeRemoteDataSource
import com.example.atnm.models.RatesResponse
import com.example.atnm.network.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRepositoryImpl @Inject constructor(private val remoteDataSource: ExchangeRemoteDataSource): ExchangeRepository {
    override suspend fun fetchExchangeData(): Resource<RatesResponse> =
        remoteDataSource.fetchExchangeData()
}