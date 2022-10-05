package com.example.cryptocurreny.repository

import com.example.cryptocurreny.model.CoinList
import com.example.cryptocurreny.service.CoinApi
import com.example.cryptocurreny.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CoinRepository @Inject constructor(
    private val api : CoinApi
){
    suspend fun getCoinList() = Resource<CoinList<Any?>>{
        val response =try {
            api.getCoinList()
        }catch (e:Exception){
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }
}