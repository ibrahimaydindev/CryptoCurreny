package com.example.cryptocurreny.repository

import com.example.cryptocurreny.model.Coin
import com.example.cryptocurreny.model.CoinList
import com.example.cryptocurreny.model.CoinListItem
import com.example.cryptocurreny.service.CoinApi
import com.example.cryptocurreny.util.Constants.API_KEY
import com.example.cryptocurreny.util.Constants.CALL_ATTRIBUTES
import com.example.cryptocurreny.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CoinRepository @Inject constructor(
    private val api: CoinApi
) {
    suspend fun getCoinList(): Resource<CoinList> {
        val response = try {
            api.getCoinList(API_KEY)
        } catch(e: Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getCoin(id: String): Resource<Coin> {
        val response = try {
            api.getCoin(API_KEY,id,CALL_ATTRIBUTES)
        } catch(e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}
