package com.discover.simple.semicrypto

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.discover.simple.core.entity.CoinsEntity
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun CoinList(viewModel: CoinViewModel, context: Context) {
    CoinInfoList(coins = viewModel.coins, context)
}

@ExperimentalCoilApi
@Composable
fun CoinInfoList(coins: Flow<PagingData<CoinsEntity.CoinEntity>>, context: Context) {
    val coinItems: LazyPagingItems<CoinsEntity.CoinEntity> = coins.collectAsLazyPagingItems()

    LazyColumn {
        itemsIndexed(coinItems) { index, item ->
            if (((index + 1) % 5) == 0) {
                item?.let {
                    CoinItem(coinData = it, onClick = {
                        Toast.makeText(context, it.id.toString(), Toast.LENGTH_SHORT).show()
                    })
                }
            } else {
                item?.let {
                    CoinWithContentItem(coinData = it, onClick = {
                        Toast.makeText(context, it.id.toString(), Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
        coinItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}