package com.maher.ad_sdk.data

import com.maher.ad_sdk.data.model.AdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Query

interface AdService {
    @GET("ad.json")
    suspend fun loadAd(): Response<AdResponse>

    @HEAD("/")
    suspend fun trackEvent(@Query("name") eventName: String)
}
