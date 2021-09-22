package com.discover.simple.core.usecase

import androidx.paging.*
import com.discover.simple.core.constant.Constant.DEFAULT_LIMIT_PAGE
import com.discover.simple.core.database.AppDatabase
import com.discover.simple.core.model.Coin
import com.discover.simple.core.model.SearchCoinMapper
import com.discover.simple.core.rx.SearchCoinsRxRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class SearchCoinsUseCase {
    @ExperimentalPagingApi
    fun execute(keyword: String): Flow<PagingData<Coin>> {
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
            val coinDao = AppDatabase.get()?.searchCoinDao()
            coinDao!!.search(keyword)
        }
        return pager.flow
            .map { pagingData ->
                pagingData.map(Executors.newSingleThreadExecutor()) {
                    SearchCoinMapper().transform(it)
                }
            }
    }
}