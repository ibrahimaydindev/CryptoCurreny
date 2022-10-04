package com.example.cryptocurreny.service

import com.example.cryptocurreny.model.CoinList
import retrofit2.http.GET

abstract class CoinApi {
    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    abstract suspend fun getCoinList(): CoinList


}