package com.maher.ad_sdk.ui

import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.maher.ad_sdk.AdSdkImpl
import com.maher.ad_sdk.AdSdkProvider
import com.maher.ad_sdk.common.AD_MODEL_EXTRA
import com.maher.ad_sdk.data.AdService
import com.maher.ad_sdk.data.RetrofitProvider
import com.maher.ad_sdk.domain.AdEventType
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.ui.theme.AdSdkTheme

class AdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adModel = intent.getParcelableExtra<AdModel>(AD_MODEL_EXTRA)
        if (adModel == null) {
            finish()
            return
        }
        val adSdk = AdSdkProvider.adSdk
        adSdk.trackEvent(AdEventType.AD_OPEN)
        setContent {
            AdSdkTheme {
                AdScreen(adModel = adModel, adSdk) {
                    finish()
                }
            }
        }
    }
}
