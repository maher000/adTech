package com.maher.ad_sdk

import android.content.Context
import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.common.AdSdkResponse
import com.maher.ad_sdk.di.AdSdkModule
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.domain.AdSdk
import java.util.UUID

object AdSdkInstance {
    private val adSdk: AdSdk = AdSdkModule.adSdk

    fun initialize(key: UUID) {
        adSdk.initialize(key)
    }

    fun show(context: Context, adModel: AdModel): AdSdkResponse<Unit> {
        return try {
            adSdk.show(context, adModel)
            AdSdkResponse.Success(Unit)
        } catch (e: AdSdkException) {
            AdSdkResponse.Error("error: ${e.message}")
        }
    }

    suspend fun load(): AdSdkResponse<AdModel> {
        return try {
            AdSdkResponse.Success(adSdk.load())
        } catch (e: AdSdkException) {
            AdSdkResponse.Error("error: ${e.message}")
        }
    }
}
