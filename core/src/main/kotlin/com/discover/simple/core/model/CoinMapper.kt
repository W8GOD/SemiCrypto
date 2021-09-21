package com.discover.simple.core.model

import com.discover.simple.core.api.response.coin.CoinListResponse
import com.discover.simple.core.entity.CoinsEntity

internal class CoinMapper {

    fun transform(response: CoinListResponse): CoinsEntity {
        return with(response) {
            CoinsEntity(
                total = response.data?.stats?.total ?: 0,
                coins = response.data?.coins?.map { coinItem ->
                    CoinsEntity.CoinEntity(
                        id = coinItem.id,
                        uuid = coinItem.uuid ?: "",
                        description = coinItem.description ?: "",
                        name = coinItem.name ?: "",
                        iconUrl = coinItem.iconUrl ?: "",
                        rank = coinItem.rank
                    )
                } ?: listOf()
            )
        }
    }
}