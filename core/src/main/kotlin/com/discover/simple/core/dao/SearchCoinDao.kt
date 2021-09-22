package com.discover.simple.core.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.discover.simple.core.entity.SearchCoinsEntity

@Dao
internal interface SearchCoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins: List<SearchCoinsEntity.SearchCoinEntity>)

    @Query("SELECT * FROM search_coins WHERE id = :keyword OR name like :keyword || '%' OR slug = :keyword OR symbol = :keyword ORDER BY rank ASC")
    fun search(keyword: String): PagingSource<Int, SearchCoinsEntity.SearchCoinEntity>

    @Query("DELETE FROM search_coins")
    fun clearCoins()
}