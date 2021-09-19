package com.discover.simple.core.dao

import androidx.room.*
import com.discover.simple.core.entities.ApiKeyEntity

@Dao
interface ApiKeyDao {
    @Query("SELECT * from api_key LIMIT 1")
    fun getApiKey(): ApiKeyEntity

    @Insert
    fun insertApiKey(apiKay: ApiKeyEntity)

    @Update
    fun updateApiKey(apiKay: ApiKeyEntity)

    @Delete
    fun deleteApiKey(apiKay: ApiKeyEntity)
}