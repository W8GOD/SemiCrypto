package com.discover.simple.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

internal data class SearchCoinsEntity(val total: Int = 0, val coins: List<SearchCoinEntity>) {

    @Entity(tableName = "search_coins")
    data class SearchCoinEntity(
        @PrimaryKey val id: Int,
        val uuid: String,
        val name: String,
        val description: String,
        val slug: String,
        val symbol: String,
        val iconUrl: String,
        val rank: Int?
    )

    @Entity(tableName = "search_coin_keys")
    data class SearchCoinKeys(
        @PrimaryKey val coinId: Int,
        val offsetKey: Int?,
        val pageKey: Int?,
        val prevKey: Int?,
        val nextKey: Int?,
        val rank: Int?
    )
}