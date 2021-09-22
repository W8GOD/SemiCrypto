package com.discover.simple.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coin(
    val id: Int,
    val uuid: String,
    val name: String,
    val description: String,
    val slug: String,
    val symbol: String,
    val iconUrl: String,
    val rank: Int?
) : Parcelable {

}