package com.maher.ad_sdk.data

import com.maher.ad_sdk.data.network.retrofit.RetrofitClientAdService
import com.maher.ad_sdk.data.network.services.AdService

object NetworkModule {
    var adService: AdService = RetrofitClientAdService()
}