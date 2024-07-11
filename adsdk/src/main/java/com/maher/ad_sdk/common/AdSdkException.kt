package com.maher.ad_sdk.common

sealed class AdSdkException : Exception() {
    /**
     * Exception thrown when the ad cannot be loaded.
     */
    data class LoadAdException(override val message: String?) : AdSdkException()
}
