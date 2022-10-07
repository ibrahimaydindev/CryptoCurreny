package com.example.cryptocurreny.di

import com.example.cryptocurreny.repository.CoinRepository
import com.example.cryptocurreny.service.CoinApi
import com.example.cryptocurreny.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCoinRepository(
        api: CoinApi
    ) = CoinRepository(api)

    @Singleton
    @Provides
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CoinApi::class.java)
    }
}