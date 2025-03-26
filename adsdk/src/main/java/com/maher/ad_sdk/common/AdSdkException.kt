package com.maher.ad_sdk.common

/**
 * Represents the different exceptions that can be thrown by the AdSdk.
 * @exception LoadAdException When the ad cannot be loaded.
 * @exception TrackAdException When the ad cannot be tracked.
 */
sealed class AdSdkException : Exception() {
    /**
     * Exception thrown when the ad cannot be loaded.
     * @param message The error message if exist or null.
     */
    data class LoadAdException(override val message: String?) : AdSdkException()

    /**
     * Exception thrown when the ad cannot be tracked.
     * @param message The error message if exist or null.
     */
    data class TrackAdException(override val message: String?) : AdSdkException()
}
