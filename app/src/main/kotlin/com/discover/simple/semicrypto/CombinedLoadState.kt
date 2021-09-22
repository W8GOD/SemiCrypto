package com.discover.simple.semicrypto

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.discover.simple.core.model.Coin
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalTime
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun CoinList(
    viewModel: CoinViewModel,
    state: MutableState<TextFieldValue>,
    onLoadCompleted: (Boolean) -> Unit,
    onLoadFailed: (String?) -> Unit,
    onItemClicked: (Coin) -> Unit
) {
    val searchedText = state.value.text
    if (searchedText.isEmpty()) {
        CoinInfoList(coins = viewModel.getCoins(), onLoadCompleted, onLoadFailed, onItemClicked)
    } else {
        CoinInfoList(
            coins = viewModel.searchCoins(searchedText),
            onLoadCompleted,
            onLoadFailed,
            onItemClicked
        )
    }
}

@ExperimentalCoilApi
@Composable
fun CoinInfoList(
    coins: Flow<PagingData<Coin>>,
    onLoadCompleted: (Boolean) -> Unit,
    onLoadFailed: (String?) -> Unit,
    onItemClicked: (Coin) -> Unit
) {
    val coinItems: LazyPagingItems<Coin> = coins.collectAsLazyPagingItems()
    LazyColumn {
        itemsIndexed(coinItems) { index, item ->
            if (((index + 1) % 5) == 0) {
                item?.let {
                    CoinItem(coinData = it, onClick = {
                        onItemClicked.invoke(it)
                    })
                }
            } else {
                item?.let {
                    CoinWithContentItem(coin = it, onClick = {
                        onItemClicked.invoke(it)
                    })
                }
            }
        }
        coinItems.apply {
            when {
                loadState.refresh is LoadState.NotLoading -> {
                    onLoadCompleted(true)
                }
                loadState.refresh is LoadState.Error -> {
                    val message = (loadState.refresh as? LoadState.Error)?.error?.message
                    onLoadFailed.invoke(message)
                }
                loadState.append is LoadState.Loading -> {
                    onLoadCompleted(true)
                }
                loadState.append is LoadState.Error -> {
                    val message = (loadState.append as? LoadState.Error)?.error?.message
                    onLoadFailed.invoke(message)
                }
            }
        }
    }
}