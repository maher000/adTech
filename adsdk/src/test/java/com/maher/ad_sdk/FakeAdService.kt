package com.maher.ad_sdk

import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.data.model.AdResponse
import com.maher.ad_sdk.data.network.services.AdService

class FakeAdService : AdService {
    companion object {
        val fakeAdResponse = AdResponse(
            static = "https://example.com/ad.jpg",
            closeDelay = 10,
            clickThrough = "https://example.com/click",
            tracking = "https://example.com/tracking"
        )

        var trackingSuccess = false

        var state: State = State.SUCCESS
    }

    override suspend fun loadAd(): AdResponse {
        return when (state) {
            State.SUCCESS -> fakeAdResponse
            State.ERROR -> throw AdSdkException.LoadAdException(message = "Error loading the ad")
        }
    }

    override suspend fun trackEvent(url: String, eventName: String) {
        trackingSuccess = false
        when (state) {
            State.SUCCESS -> trackingSuccess = true
            State.ERROR -> throw AdSdkException.TrackAdException(message = "Error tracking the ad")
        }
    }

    enum class State {
        SUCCESS,
        ERROR
    }
}