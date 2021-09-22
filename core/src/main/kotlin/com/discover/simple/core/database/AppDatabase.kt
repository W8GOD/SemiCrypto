package com.discover.simple.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.discover.simple.core.dao.CoinDao
import com.discover.simple.core.dao.CoinKeysDao
import com.discover.simple.core.dao.SearchCoinDao
import com.discover.simple.core.dao.SearchCoinKeysDao
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.entity.SearchCoinsEntity

@Database(
    entities = [CoinsEntity.CoinEntity::class, CoinsEntity.CoinKeys::class,
        SearchCoinsEntity.SearchCoinEntity::class, SearchCoinsEntity.SearchCoinKeys::class],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun coinKeysDao(): CoinKeysDao

    abstract fun coinDao(): CoinDao

    abstract fun searchCoinKeysDao(): SearchCoinKeysDao

    abstract fun searchCoinDao(): SearchCoinDao

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