package com.discover.simple.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.discover.simple.core.dao.ApiKeyDao
import com.discover.simple.core.dao.CoinDao
import com.discover.simple.core.dao.CoinKeysDao
import com.discover.simple.core.entity.ApiKeyEntity
import com.discover.simple.core.entity.CoinsEntity

@Database(
    entities = [ApiKeyEntity::class,
        CoinsEntity.CoinEntity::class,
        CoinsEntity.CoinKeys::class],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun apiKeyDao(): ApiKeyDao

    abstract fun coinKeysDao(): CoinKeysDao

    abstract fun coinDao(): CoinDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun get(): AppDatabase? {
            return INSTANCE
        }

        fun init(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "simi_crypto_db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}