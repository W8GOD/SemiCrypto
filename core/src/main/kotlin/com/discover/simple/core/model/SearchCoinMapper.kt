package com.discover.simple.core.model

import com.discover.simple.core.entity.SearchCoinsEntity

internal class SearchCoinMapper {

    fun transform(coins: SearchCoinsEntity.SearchCoinEntity): Coin {
        return with(coins) {
            Coin(
                id = id,
                uuid = uuid,
                name = name,
                description = description,
                slug = slug,
                symbol = symbol,
                iconUrl = iconUrl,
                rank = rank
            )
        }
    }
}