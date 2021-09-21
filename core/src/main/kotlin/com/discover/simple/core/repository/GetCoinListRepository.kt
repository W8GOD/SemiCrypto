package com.discover.simple.core.repository

import com.discover.simple.core.api.RetrofitClient
import com.discover.simple.core.api.response.coin.CoinListResponse
import io.reactivex.Single

class GetCoinListRepository {

    internal fun getCoinList(offset: Int, limit: Int): Single<CoinListResponse> {
        return RetrofitClient.apiService.getCoinList(offset = offset, limit = limit)
    }
}