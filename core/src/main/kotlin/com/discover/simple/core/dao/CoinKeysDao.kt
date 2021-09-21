package com.discover.simple.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.discover.simple.core.entity.CoinsEntity

@Dao
internal interface CoinKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(keys: List<CoinsEntity.CoinKeys>)

    @Query("SELECT * FROM coin_keys WHERE coinId = :coinId")
    fun getKeysByCoinId(coinId: String): CoinsEntity.CoinKeys

    @Query("SELECT * FROM coin_keys")
    fun getAllKeys(): List<CoinsEntity.CoinKeys>

    @Query("SELECT * FROM coin_keys ORDER BY rank DESC LIMIT 1")
    fun getLastKeys(): CoinsEntity.CoinKeys

    @Query("SELECT * FROM coin_keys ORDER BY rank ASC LIMIT 1")
    fun getFirstKeys(): CoinsEntity.CoinKeys

    @Query("DELETE FROM coin_keys")
    fun clearKeys()
}