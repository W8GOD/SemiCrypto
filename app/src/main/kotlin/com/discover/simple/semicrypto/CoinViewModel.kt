package com.discover.simple.semicrypto

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class CoinViewModel : ViewModel() {
    val coins: Flow<PagingData<CoinsEntity.CoinEntity>> = GetCoinsUseCase().execute()
}