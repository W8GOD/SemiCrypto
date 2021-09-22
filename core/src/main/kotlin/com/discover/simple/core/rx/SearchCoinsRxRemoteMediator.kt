package com.discover.simple.core.rx

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.discover.simple.core.constant.Constant.DEFAULT_LIMIT_PAGE
import com.discover.simple.core.database.AppDatabase
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.model.CoinMapper
import com.discover.simple.core.repository.GetCoinListRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException

@ExperimentalPagingApi
class SearchCoinsRxRemoteMediator(private val keyword: String) :
    RxRemoteMediator<Int, CoinsEntity.CoinEntity>() {

    private val repository = GetCoinListRepository()
    private val coinKeysDao = AppDatabase.get()?.coinKeysDao()
    private val coinDao = AppDatabase.get()?.coinDao()

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, CoinsEntity.CoinEntity>
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
                        keyword = keyword,
                        offset = offset,
                        limit = DEFAULT_LIMIT_PAGE
                    )
                        .map { CoinMapper().transform(it) }
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

    private fun insertToDb(offset: Int, loadType: LoadType, data: CoinsEntity): CoinsEntity {
        if (loadType == LoadType.REFRESH) {
            coinDao?.clearCoins()
            coinKeysDao?.clearKeys()
        }
        val page = (offset / DEFAULT_LIMIT_PAGE) + 1
        val offsetKey = (page - 1) * DEFAULT_LIMIT_PAGE
        val prevKey = if (page == 1) null else (page - 1) * DEFAULT_LIMIT_PAGE
        val nextKey = if (offset > data.total) null else page * DEFAULT_LIMIT_PAGE
        val keys = data.coins.map { coinItem ->
            CoinsEntity.CoinKeys(
                coinId = coinItem.id,
                offsetKey = offsetKey,
                pageKey = page,
                prevKey = prevKey,
                nextKey = nextKey,
                rank = coinItem.rank
            )
        }
        coinKeysDao?.insertAll(keys)
        coinDao?.insertAll(data.coins)
        return data
    }

    private fun getKeyForLastItem(): CoinsEntity.CoinKeys? {
        return coinKeysDao?.getLastKeys()
    }

    private fun getKeyForFirstItem(): CoinsEntity.CoinKeys? {
        return coinKeysDao?.getFirstKeys()
    }

    private fun getKeyClosestToCurrentPosition(state: PagingState<Int, CoinsEntity.CoinEntity>): CoinsEntity.CoinKeys? {
        return state.anchorPosition?.let { position ->
            coinKeysDao?.getAllKeys()?.get(position)
        }
    }

    companion object {
        const val INVALID_PAGE = -1
    }
}