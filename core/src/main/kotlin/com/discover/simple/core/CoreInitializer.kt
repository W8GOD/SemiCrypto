package com.discover.simple.core

import android.content.Context
import com.discover.simple.core.database.AppDatabase

internal object CoreInitializer {

    @Synchronized
    fun init(context: Context) {
        AppDatabase.init(context)
    }
}