package com.discover.simple.core.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_key")
data class ApiKeyEntity(
    @PrimaryKey val id: String = "API_KEY_ID",
    @ColumnInfo(name = "apiKey") val apiKey: String
)
