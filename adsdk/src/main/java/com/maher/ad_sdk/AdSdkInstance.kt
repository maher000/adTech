package com.maher.ad_sdk

import android.content.Context
import com.maher.ad_sdk.AdSdk
import com.maher.ad_sdk.AdSdkImpl
import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.domain.AdEventType
import com.maher.ad_sdk.domain.AdModel
import java.util.UUID

object AdSdkInstance {
    private val adSdk: AdSdk = AdSdkImpl()

    fun initialize(key: UUID) {
        adSdk.initialize(key)
    }

    @Throws(AdSdkException::class)
    fun show(context: Context, adModel: AdModel) {
        adSdk.show(context, adModel)
    }

    @Throws(AdSdkException::class)
    suspend fun load(): AdModel {
        return adSdk.load()
    }
}
