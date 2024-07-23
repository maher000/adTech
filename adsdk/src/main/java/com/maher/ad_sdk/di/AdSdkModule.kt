package com.maher.ad_sdk.di

import com.maher.ad_sdk.data.AdSdkImpl
import com.maher.ad_sdk.data.NetworkModule
import com.maher.ad_sdk.domain.AdSdk

object AdSdkModule {
    var adSdk: AdSdk = AdSdkImpl(adService = NetworkModule.adService)
}