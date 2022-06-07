package com.example.atnm.data.remotedatasource

import com.example.atnm.models.RatesResponse
import com.example.atnm.network.ApiService
import com.example.atnm.network.RemoteServicesHandler
import com.example.atnm.network.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val servicesHandler: RemoteServicesHandler
) {
    suspend fun fetchExchangeData(): Resource<RatesResponse> =
        servicesHandler.makeTheCallAndHandleResponse(
            serviceCall = { apiService.getRates() },
            mapSuccess = {
                Resource.Success(it.body())
            }
        )
}