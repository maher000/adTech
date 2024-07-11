package com.maher.adsdkapi

import android.content.Context
import com.maher.ad_sdk.AdSdk
import com.maher.ad_sdk.AdSdkImpl
import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.domain.AdEventType
import java.util.UUID

object AdSdkInstance {
    private val adSdk: AdSdk = AdSdkImpl()

    fun initialize(key: UUID) {
        adSdk.initialize(key)
    }

    @Throws(AdSdkException::class)
    fun show(context: Context) {
        adSdk.show(context)
    }

    fun trackEvent(event: AdEventType) {
        adSdk.trackEvent(event)
    }
}
