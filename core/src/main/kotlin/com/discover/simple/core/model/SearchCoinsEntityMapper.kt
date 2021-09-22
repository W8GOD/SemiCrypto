package com.discover.simple.core.model

import com.discover.simple.core.api.response.coin.CoinListResponse
import com.discover.simple.core.entity.SearchCoinsEntity

internal class SearchCoinsEntityMapper {

    fun transform(response: CoinListResponse): SearchCoinsEntity {
        return with(response) {
            SearchCoinsEntity(
                total = response.data?.stats?.total ?: 0,
                coins = response.data?.coins?.map { coinItem ->
                    SearchCoinsEntity.SearchCoinEntity(
                        id = coinItem.id,
                        uuid = coinItem.uuid ?: "",
                        description = coinItem.description ?: "",
                        slug = coinItem.slug ?: "",
                        symbol = coinItem.symbol ?: "",
                        name = coinItem.name ?: "",
                        iconUrl = coinItem.iconUrl ?: "",
                        rank = coinItem.rank
                    )
                } ?: listOf()
            )
        }
    }
}