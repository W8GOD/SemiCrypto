package com.discover.simple.core.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinsEntity(val total: Int = 0, val coins: List<CoinEntity>) : Parcelable {

    @Parcelize
    @Entity(tableName = "coins")
    data class CoinEntity(
        @PrimaryKey val id: Int,
        val uuid: String,
        val name: String,
        val description: String,
        val iconUrl: String,
        val rank: Int?
    ) : Parcelable

    @Parcelize
    @Entity(tableName = "coin_keys")
    data class CoinKeys(
        @PrimaryKey val coinId: Int,
        val offsetKey: Int?,
        val pageKey: Int?,
        val prevKey: Int?,
        val nextKey: Int?,
        val rank: Int?
    ) : Parcelable
}