package com.example.cryptocurreny.model


import com.google.gson.annotations.SerializedName

data class CoinListItem(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("price")
    val price: String
)