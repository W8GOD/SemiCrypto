package com.discover.simple.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.discover.simple.core.entity.SearchCoinsEntity

@Dao
internal interface SearchCoinKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(keys: List<SearchCoinsEntity.SearchCoinKeys>)

    @Query("SELECT * FROM search_coin_keys WHERE coinId = :coinId")
    fun getKeysByCoinId(coinId: String): SearchCoinsEntity.SearchCoinKeys

    @Query("SELECT * FROM search_coin_keys")
    fun getAllKeys(): List<SearchCoinsEntity.SearchCoinKeys>

    @Query("SELECT * FROM search_coin_keys ORDER BY rank DESC LIMIT 1")
    fun getLastKeys(): SearchCoinsEntity.SearchCoinKeys

    @Query("SELECT * FROM search_coin_keys ORDER BY rank ASC LIMIT 1")
    fun getFirstKeys(): SearchCoinsEntity.SearchCoinKeys

    @Query("DELETE FROM search_coin_keys")
    fun clearKeys()
}