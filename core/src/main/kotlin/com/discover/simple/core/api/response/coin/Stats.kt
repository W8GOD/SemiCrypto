package com.discover.simple.core.api.response.coin

internal data class Stats(
    val limit: Int?,
    val offset: Int?,
    val order: String?,
    val total: Int?,
)