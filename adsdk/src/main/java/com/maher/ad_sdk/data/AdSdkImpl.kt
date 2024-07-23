package com.maher.ad_sdk.data

import android.content.Context
import android.content.Intent
import android.util.Log
import com.maher.ad_sdk.common.AD_MODEL_EXTRA
import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.data.model.toAdResponseDomain
import com.maher.ad_sdk.data.network.services.AdService
import com.maher.ad_sdk.domain.AdEventType
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.domain.AdSdk
import com.maher.ad_sdk.ui.AdActivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

private const val TAG = "AdSdkImpl"

class AdSdkImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val adService: AdService
) : AdSdk {

    private lateinit var key: UUID

    private var trackLink: String? = null

    override fun initialize(key: UUID) {
        this.key = key
    }

    override fun show(context: Context, adModel: AdModel) {
        val intent = Intent(context, AdActivity::class.java).apply {
            putExtra(AD_MODEL_EXTRA, adModel)
        }
        context.startActivity(intent)
    }

    @Throws(AdSdkException.TrackAdException::class)
    override suspend fun trackEvent(event: AdEventType) {
        withContext(dispatcher) {
            trackLink?.let {
                val response = adService.trackEvent(url = it, eventName = event.eventName)
                Log.d(TAG, response.toString())
            }
        }
    }

    /**
     * Load the ad from the server.
     * @throws AdSdkException.LoadAdException If the ad cannot be loaded.
     */
    @Throws(AdSdkException.LoadAdException::class)
    override suspend fun load(): AdModel {
        try {
            return withContext(dispatcher) {
                val response = adService.loadAd()
                response.toAdResponseDomain().also {
                    trackLink = it.tracking
                }
            }
        } catch (e: AdSdkException.LoadAdException) {
            throw e
        } catch (e: Throwable) {
            throw AdSdkException.LoadAdException(message = "${e.message}")
        }
    }
}