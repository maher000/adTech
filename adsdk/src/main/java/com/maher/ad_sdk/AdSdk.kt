package com.maher.ad_sdk

import android.content.Context
import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.domain.AdEventType
import com.maher.ad_sdk.domain.AdModel
import java.util.UUID
import kotlin.jvm.Throws

interface AdSdk {

    /**
     * Initialize the ad SDK.
     * @param key The key for the ad SDK.
     */
    fun initialize(key: UUID)

    /**
     * Show the ad in the screen.
     * Throws an exception if the ad cannot be displayed.
     */
    @Throws(AdSdkException::class)
    fun show(context: Context)


    fun trackEvent(event: AdEventType)

}
