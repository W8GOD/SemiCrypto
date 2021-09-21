package com.discover.simple.semicrypto

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.core.rx.GetCoinsUserCase
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class EmployeeViewModel : ViewModel() {
    val coins: Flow<PagingData<CoinsEntity.CoinEntity>> = GetCoinsUserCase().execute()
}