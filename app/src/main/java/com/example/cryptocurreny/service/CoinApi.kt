package com.example.cryptocurreny.service

import com.example.cryptocurreny.model.Coin
import com.example.cryptocurreny.model.CoinList
import com.example.cryptocurreny.model.CoinListItem
import retrofit2.http.GET
import retrofit2.http.Query

abstract class CoinApi {
    @GET("prices")
    abstract suspend fun getCoinList(
        @Query("key") key: String,
    ): CoinList

    @GET("currencies")
    abstract suspend fun getCoin(
        @Query("key") key: String,
        @Query("ids") id : String,
        @Query("attributes") attributes: String
    ): Coin
}