package com.maher.ad_sdk

import android.content.Context
import android.content.Intent
import android.util.Log
import com.maher.ad_sdk.common.AD_MODEL_EXTRA
import com.maher.ad_sdk.common.AdSdkException
import com.maher.ad_sdk.data.AdService
import com.maher.ad_sdk.data.RetrofitProvider
import com.maher.ad_sdk.data.model.toAdResponseDomain
import com.maher.ad_sdk.domain.AdEventType
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.ui.AdActivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

private const val TAG = "AdSdkImpl"

internal class AdSdkImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AdSdk {

    private lateinit var key: UUID

    private val adService: AdService by lazy {
        RetrofitProvider.retrofit.create(AdService::class.java)
    }

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
     * @throws AdSdkException If the ad cannot be loaded.
     */
    @Throws(AdSdkException::class)
    override suspend fun load(): AdModel {
        try {
            return withContext(dispatcher) {
                val response = adService.loadAd()
                response.body()?.toAdResponseDomain()?.also {
                    trackLink = it.tracking
                } ?: throw AdSdkException.LoadAdException(
                    message = "Error loading the ad"
                )
            }
        } catch (e: HttpException) {
            throw AdSdkException.LoadAdException(message = "code = ${e.code()} message = ${e.message}")
        } catch (e: Throwable) {
            throw AdSdkException.LoadAdException(message = "${e.message}")
        }
    }
}