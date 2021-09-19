package com.discover.simple.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.discover.simple.core.dao.ApiKeyDao
import com.discover.simple.core.entities.ApiKeyEntity

@Database(entities = [ApiKeyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun apiKeyDao(): ApiKeyDao

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