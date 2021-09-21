package com.discover.simple.core.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.discover.simple.core.database.AppDatabase
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.rx.GetCoinsRxRemoteMediator
import kotlinx.coroutines.flow.Flow

const val DEFAULT_LIMIT_PAGE = 10

class GetCoinsUserCase {

    @ExperimentalPagingApi
    fun execute(): Flow<PagingData<CoinsEntity.CoinEntity>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = DEFAULT_LIMIT_PAGE,
                enablePlaceholders = true,
                prefetchDistance = 5,
                initialLoadSize = DEFAULT_LIMIT_PAGE
            ),
            initialKey = null,
            remoteMediator = GetCoinsRxRemoteMediator()
        ) {
            val coinDao = AppDatabase.get()?.coinDao()
            coinDao!!.getAll()
        }
        return pager.flow
    }
}