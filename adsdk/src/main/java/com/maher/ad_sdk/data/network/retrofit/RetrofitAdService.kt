package com.maher.ad_sdk.data.network.retrofit

import com.maher.ad_sdk.data.model.AdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitAdService {
    @GET("ad.json")
    suspend fun loadAd(): Response<AdResponse>

    @HEAD
    suspend fun trackEvent(@Url url: String, @Query("name") eventName: String): Response<Void>
}
