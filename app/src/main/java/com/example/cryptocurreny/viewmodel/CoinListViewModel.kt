package com.example.cryptocurreny.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurreny.model.CoinListItem
import com.example.cryptocurreny.repository.CoinRepository
import com.example.cryptocurreny.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CoinRepository) : ViewModel() {
    var coinList = mutableStateOf<List<CoinListItem>>(listOf())
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf("")

    fun loadCoins() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getCoinList()) {
                is Resource.Success -> {
                    val coinItems = result.data
                    val coinListItems = result.data!!.mapIndexed { index, coinListItem ->
                        CoinListItem(coinListItem.currency, coinListItem.price)
                    }
                    isError.value = ""
                    isLoading.value = false
                    coinList.value += coinListItems
                }
                is Resource.Error -> {
                    isError.value = result.message!!
                    isLoading.value = false
                }
                else -> {
                    isError.value = "An unknown error occured"
                }
            }
        }
    }
}