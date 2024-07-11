package com.maher.ad_sdk.data

import retrofit2.http.HEAD
import retrofit2.http.Query

interface AdTrackingService {

    @HEAD("/")
    suspend fun trackEvent(@Query("name") eventName: String)
}
