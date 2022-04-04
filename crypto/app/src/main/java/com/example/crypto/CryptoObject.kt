package com.example.crypto

class CryptoObject(
    var data: List<Coin>
) {

}

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String,
) {

}


