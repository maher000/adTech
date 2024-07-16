package com.maher.ad_sdk.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.maher.ad_sdk.AdSdkInstance
import com.maher.ad_sdk.common.AD_MODEL_EXTRA
import com.maher.ad_sdk.domain.AdEventType
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.ui.theme.AdSdkTheme
import kotlinx.coroutines.launch

class AdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adModel = intent.getParcelableExtra<AdModel>(AD_MODEL_EXTRA)
        if (adModel == null) {
            finish()
            return
        }
        lifecycleScope.launch {
            AdSdkInstance.trackEvent(AdEventType.AD_OPEN)
        }
        setContent {
            AdSdkTheme {
                AdScreen(
                    adModel = adModel,
                    onClickCallback = {
                        lifecycleScope.launch {
                            AdSdkInstance.trackEvent(AdEventType.AD_CLICK)
                        }
                    },
                    onCloseCallback = {
                        lifecycleScope.launch {
                            AdSdkInstance.trackEvent(AdEventType.AD_CLOSE)
                        }
                        finish()
                    })
            }
        }
    }
}
