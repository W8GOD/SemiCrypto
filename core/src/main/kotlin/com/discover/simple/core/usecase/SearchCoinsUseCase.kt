package com.discover.simple.core.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.discover.simple.core.constant.Constant.DEFAULT_LIMIT_PAGE
import com.discover.simple.core.database.AppDatabase
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.rx.SearchCoinsRxRemoteMediator
import kotlinx.coroutines.flow.Flow

class SearchCoinsUseCase {
    @ExperimentalPagingApi
    fun execute(keyword: String): Flow<PagingData<CoinsEntity.CoinEntity>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = DEFAULT_LIMIT_PAGE,
                enablePlaceholders = true,
                prefetchDistance = 5,
                initialLoadSize = DEFAULT_LIMIT_PAGE
            ),
            initialKey = null,
            remoteMediator = SearchCoinsRxRemoteMediator(keyword = keyword)
        ) {
            val coinDao = AppDatabase.get()?.coinDao()
            coinDao!!.search(keyword)
        }
        return pager.flow
    }
}