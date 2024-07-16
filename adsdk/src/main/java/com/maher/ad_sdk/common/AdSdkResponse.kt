package com.maher.ad_sdk.common

sealed class AdSdkResponse<T> {
    class Success<T>(val data: T) : AdSdkResponse<T>()
    class Error<T>(val message: String) : AdSdkResponse<T>()
}