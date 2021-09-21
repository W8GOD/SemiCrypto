package com.discover.simple.core.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.discover.simple.core.entity.CoinsEntity

@Dao
internal interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins: List<CoinsEntity.CoinEntity>)

    @Query("SELECT * FROM coins ORDER BY rank ASC")
    fun getAll(): PagingSource<Int, CoinsEntity.CoinEntity>

    @Query("DELETE FROM coins")
    fun clearCoins()
}