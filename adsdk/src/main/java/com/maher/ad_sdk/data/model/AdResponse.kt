package com.maher.ad_sdk.data.model

import com.google.gson.annotations.SerializedName
import com.maher.ad_sdk.domain.AdModel


data class AdResponse(
    val static: String,
    @SerializedName("close_delay") val closeDelay: Int,
    val tracking: String,
    @SerializedName("clickthrough") val clickThrough: String
)

fun AdResponse.toAdResponseDomain(): AdModel {
    return AdModel(
        static = static,
        closeDelay = closeDelay,
        tracking = tracking,
        clickThrough = clickThrough
    )
}