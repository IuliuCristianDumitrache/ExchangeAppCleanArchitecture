package com.example.atnm.di

import android.content.Context
import com.example.atnm.data.remotedatasource.ExchangeRemoteDataSource
import com.example.atnm.data.repository.ExchangeRepository
import com.example.atnm.data.repository.ExchangeRepositoryImpl
import com.example.atnm.domainlayer.FetchExchangeUseCase
import com.example.atnm.network.ApiFactory
import com.example.atnm.network.ApiService
import com.example.atnm.network.RemoteServicesHandler
import com.example.atnm.network.exceptions.NetworkExceptionHandler
import com.example.atnm.utils.StringResource
import com.example.atnm.utils.StringResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.getClient()
    }

    @Provides
    fun provideStringResource(@ApplicationContext context: Context): StringResource {
        return StringResourceProvider(context)
    }

    @Provides
    fun provideExchangeDataSource(
        apiService: ApiService,
        handler: RemoteServicesHandler
    ): ExchangeRemoteDataSource {
        return ExchangeRemoteDataSource(apiService, handler)
    }

    @Provides
    fun provideRemoteServiceHandler(networkExceptionHandler: NetworkExceptionHandler): RemoteServicesHandler {
        return RemoteServicesHandler(networkExceptionHandler)
    }

    @Provides
    fun provideNetworkExceptionHandler(stringResource: StringResource): NetworkExceptionHandler {
        return NetworkExceptionHandler(stringResource)
    }

    @Provides
    fun provideExchangeRepository(remoteDataSource: ExchangeRemoteDataSource): ExchangeRepositoryImpl {
        return ExchangeRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideExchangeUseCase(repository: ExchangeRepositoryImpl): FetchExchangeUseCase {
        return FetchExchangeUseCase(repository)
    }
}