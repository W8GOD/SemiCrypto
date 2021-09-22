package com.discover.simple.core.rx

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.discover.simple.core.constant.Constant.DEFAULT_LIMIT_PAGE
import com.discover.simple.core.database.AppDatabase
import com.discover.simple.core.entity.SearchCoinsEntity
import com.discover.simple.core.model.SearchCoinsEntityMapper
import com.discover.simple.core.repository.GetCoinListRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException

@ExperimentalPagingApi
internal class SearchCoinsRxRemoteMediator(private val prefix: String) :
    RxRemoteMediator<Int, SearchCoinsEntity.SearchCoinEntity>() {

    private val repository = GetCoinListRepository()
    private val searchCoinKeysDao = AppDatabase.get()?.searchCoinKeysDao()
    private val searchCoinDao = AppDatabase.get()?.searchCoinDao()

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, SearchCoinsEntity.SearchCoinEntity>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (it) {
                    LoadType.REFRESH -> {
                        val keys = getKeyClosestToCurrentPosition(state)
                        keys?.nextKey?.minus(DEFAULT_LIMIT_PAGE)?.coerceAtLeast(0) ?: 0
                    }
                    LoadType.PREPEND -> {
                        val keys = getKeyForFirstItem()
                            ?: throw InvalidObjectException("Result is empty")
                        keys.prevKey ?: INVALID_PAGE
                    }
                    LoadType.APPEND -> {
                        val keys = getKeyForLastItem()
                            ?: throw InvalidObjectException("Result is empty")
                        keys.nextKey ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { offset ->
                if (offset == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    repository.searchCoinList(
                        prefix = prefix,
                        offset = offset,
                        limit = DEFAULT_LIMIT_PAGE
                    )
                        .map { SearchCoinsEntityMapper().transform(it) }
                        .map { insertToDb(offset, loadType, it) }
                        .map<MediatorResult> {
                            MediatorResult.Success(endOfPaginationReached = offset > it.total)
                        }
                        .onErrorReturn { MediatorResult.Error(it) }
                }

            }
            .onErrorReturn {
                MediatorResult.Error(it)
            }
    }

    private fun insertToDb(
        offset: Int,
        loadType: LoadType,
        data: SearchCoinsEntity
    ): SearchCoinsEntity {
        if (loadType == LoadType.REFRESH) {
            searchCoinDao?.clearCoins()
            searchCoinKeysDao?.clearKeys()
        }
        val page = (offset / DEFAULT_LIMIT_PAGE) + 1
        val offsetKey = (page - 1) * DEFAULT_LIMIT_PAGE
        val prevKey = if (page == 1) null else (page - 1) * DEFAULT_LIMIT_PAGE
        val nextKey = if (offset > data.total) null else page * DEFAULT_LIMIT_PAGE
        val keys = data.coins.map { coinItem ->
            SearchCoinsEntity.SearchCoinKeys(
                coinId = coinItem.id,
                offsetKey = offsetKey,
                pageKey = page,
                prevKey = prevKey,
                nextKey = nextKey,
                rank = coinItem.rank
            )
        }
        searchCoinKeysDao?.insertAll(keys)
        searchCoinDao?.insertAll(data.coins)
        return data
    }

    private fun getKeyForLastItem(): SearchCoinsEntity.SearchCoinKeys? {
        return searchCoinKeysDao?.getLastKeys()
    }

    private fun getKeyForFirstItem(): SearchCoinsEntity.SearchCoinKeys? {
        return searchCoinKeysDao?.getFirstKeys()
    }

    private fun getKeyClosestToCurrentPosition(state: PagingState<Int, SearchCoinsEntity.SearchCoinEntity>): SearchCoinsEntity.SearchCoinKeys? {
        return state.anchorPosition?.let { position ->
            searchCoinKeysDao?.getAllKeys()?.get(position)
        }
    }

    companion object {
        const val INVALID_PAGE = -1
    }
}