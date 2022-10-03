package com.example.cryptocurreny.service

import retrofit2.http.GET

class CoinApi {
    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCoinList:Unit

}