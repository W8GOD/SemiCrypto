package com.discover.simple.core.api

import com.discover.simple.core.api.response.coin.CoinListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitService {
    @GET("coins")
    fun getCoinList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<CoinListResponse>

    @GET("coins")
    fun searchCoinList(
        @Query("prefix") prefix: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<CoinListResponse>
}