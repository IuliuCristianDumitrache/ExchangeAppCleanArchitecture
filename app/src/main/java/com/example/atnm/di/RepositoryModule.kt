package com.example.atnm.di

import com.example.atnm.data.repository.ExchangeRepository
import com.example.atnm.data.repository.ExchangeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindExchangeRepository(exchangeRepositoryImpl: ExchangeRepositoryImpl): ExchangeRepository
}