package com.discover.simple.core.api.response.coin

internal data class Data(
    val base: Base?,
    val coins: List<Coin>?,
    val stats: Stats?
)