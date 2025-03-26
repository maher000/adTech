package com.maher.ad_sdk.data.network.services

import com.maher.ad_sdk.data.model.AdResponse

interface AdService {

    suspend fun loadAd(): AdResponse

    suspend fun trackEvent(url: String, eventName: String)
}
