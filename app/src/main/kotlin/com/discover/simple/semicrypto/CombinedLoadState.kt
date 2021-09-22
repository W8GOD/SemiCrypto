package com.discover.simple.semicrypto

import android.content.Context
import android.widget.Toast
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
    context: Context,
    viewModel: CoinViewModel,
    state: MutableState<TextFieldValue>, onLoaded: (Boolean) -> Unit
) {
    val searchedText = state.value.text
    if (searchedText.isEmpty()) {
        CoinInfoList(coins = viewModel.getCoins(), context, onLoaded)
    } else {
        CoinInfoList(coins = viewModel.searchCoins(searchedText), context, onLoaded)
    }
}

@ExperimentalCoilApi
@Composable
fun CoinInfoList(coins: Flow<PagingData<Coin>>, context: Context, onLoaded: (Boolean) -> Unit) {
    val coinItems: LazyPagingItems<Coin> = coins.collectAsLazyPagingItems()
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
                    CoinWithContentItem(coin = it, onClick = {
                        Toast.makeText(context, it.id.toString(), Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
        coinItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    onLoaded.invoke(false)
                }
                loadState.refresh is LoadState.Error -> {
                    val message = (loadState.refresh as? LoadState.Error)?.error?.message
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                loadState.append is LoadState.Loading -> {
                    onLoaded.invoke(false)
                }
                loadState.append is LoadState.Error -> {
                    val message = (loadState.append as? LoadState.Error)?.error?.message
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}