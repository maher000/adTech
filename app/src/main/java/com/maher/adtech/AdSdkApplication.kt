package com.maher.adtech

import android.app.Application
import com.maher.ad_sdk.AdSdkInstance
import java.util.UUID

class AdSdkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AdSdkInstance.initialize(UUID.randomUUID())
    }
}