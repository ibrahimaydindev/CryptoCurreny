package com.example.cryptocurreny.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurreny.model.CoinListItem
import com.example.cryptocurreny.repository.CoinRepository
import com.example.cryptocurreny.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CoinRepository) : ViewModel() {
    var coinList = mutableStateOf<List<CoinListItem>>(listOf())
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf("")
    private var newCoinList = listOf<CoinListItem>()
    private var isSearchStarting = true

    init {
        loadCoins()
    }

    private fun loadCoins() {
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
                    isError.value = "Bilinmeyen hata"
                }
            }
        }
    }

    fun searchCoinList(query: String) {
        val listToSearch = if (isSearchStarting) {
            coinList.value
        } else {
            newCoinList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                coinList.value = newCoinList
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.currency.contains(query.trim(), ignoreCase = true)
            }
            if (isSearchStarting) {
                newCoinList = coinList.value
                isSearchStarting = false
            }
            coinList.value = results
        }
    }
}