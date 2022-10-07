package com.example.cryptocurreny.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cryptocurreny.model.Coin
import com.example.cryptocurreny.repository.CoinRepository
import com.example.cryptocurreny.util.Resource
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(private val repository: CoinRepository) :
    ViewModel() {
    suspend fun getCoinDetail(id: String): Resource<Coin> {
        return repository.getCoin(id)
    }
}