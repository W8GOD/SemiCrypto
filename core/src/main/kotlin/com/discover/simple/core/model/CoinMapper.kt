package com.discover.simple.core.model

import com.discover.simple.core.entity.CoinsEntity

internal class CoinMapper {

    fun transform(coins: CoinsEntity.CoinEntity): Coin {
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