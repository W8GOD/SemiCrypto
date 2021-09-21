package com.discover.simple.core.api.response.coin

internal data class Data(
    val coins: List<Coin>,
    val stats: Stats
)