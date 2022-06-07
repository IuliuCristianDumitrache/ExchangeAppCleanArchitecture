package com.example.atnm.network

import com.example.atnm.network.exceptions.NetworkNotAvailableException
import com.example.atnm.utils.connectivity.NetworkConnection
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkNotAvailableInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (NetworkConnection.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkNotAvailableException()
        }
    }
}