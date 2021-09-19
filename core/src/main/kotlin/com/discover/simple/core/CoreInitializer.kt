package com.discover.simple.core

import android.content.Context
import com.discover.simple.core.database.AppDatabase

object CoreInitializer {

    @Synchronized
    fun init(context: Context) {
        AppDatabase.init(context)
    }
}