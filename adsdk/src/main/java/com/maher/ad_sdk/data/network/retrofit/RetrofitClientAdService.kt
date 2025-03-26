package com.maher.ad_sdk.data.network.retrofit

import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.data.model.AdResponse
import com.maher.ad_sdk.data.network.services.AdService
import retrofit2.HttpException

class RetrofitClientAdService : AdService {

    private val adService: RetrofitAdService by lazy {
        RetrofitProvider.retrofit.create(RetrofitAdService::class.java)
    }

    override suspend fun loadAd(): AdResponse {
        try {
            val response = adService.loadAd()
            return response.body()
                ?: throw AdSdkException.LoadAdException(message = "Error loading the ad")
        } catch (e: HttpException) {
            throw AdSdkException.LoadAdException(message = "code = ${e.code()} message = ${e.message}")
        } catch (e: Throwable) {
            throw AdSdkException.LoadAdException(message = "${e.message}")
        }
    }

    override suspend fun trackEvent(url: String, eventName: String) {
        try {
            val response = adService.trackEvent(url = url, eventName = eventName)
            if (response.code() != 200) {
                throw AdSdkException.TrackAdException(message = "Error tracking the ad")
            }
        } catch (e: HttpException) {
            throw AdSdkException.TrackAdException(message = "code = ${e.code()} message = ${e.message}")
        } catch (e: Throwable) {
            throw AdSdkException.TrackAdException(message = "${e.message}")
        }
    }
}
