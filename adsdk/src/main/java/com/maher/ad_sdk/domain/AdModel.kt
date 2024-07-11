package com.maher.ad_sdk.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdModel(
    val static: String,
    val closeDelay: Int,
    val tracking: String,
    val clickThrough: String
) : Parcelable