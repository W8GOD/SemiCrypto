package com.discover.simple.semicrypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.discover.simple.core.model.Coin
import com.discover.simple.core.usecase.GetCoinsUseCase
import com.discover.simple.core.usecase.SearchCoinsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalPagingApi
class CoinViewModel : ViewModel() {

    fun getCoins(): Flow<PagingData<Coin>> {
        return GetCoinsUseCase().execute().cachedIn(viewModelScope)
    }

    @ExperimentalTime
    @FlowPreview
    fun searchCoins(prefix: String): Flow<PagingData<Coin>> {
        return SearchCoinsUseCase()
            .execute(prefix)
            .debounce(Duration.milliseconds(300))
            .cachedIn(viewModelScope)
    }
}