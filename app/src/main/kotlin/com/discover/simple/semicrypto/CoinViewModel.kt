package com.discover.simple.semicrypto

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.usecase.GetCoinsUseCase
import com.discover.simple.core.usecase.SearchCoinsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalPagingApi
class CoinViewModel : ViewModel() {
    val getCoins: Flow<PagingData<CoinsEntity.CoinEntity>> = GetCoinsUseCase().execute()

    @ExperimentalTime
    @FlowPreview
    fun searchCoins(keyword: String): Flow<PagingData<CoinsEntity.CoinEntity>> {
        return SearchCoinsUseCase().execute(keyword).debounce(Duration.milliseconds(300))
    }
}