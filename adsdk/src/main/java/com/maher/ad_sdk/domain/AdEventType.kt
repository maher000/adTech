package com.maher.ad_sdk.domain

enum class AdEventType(val eventName: String) {
    AD_CLICK("ad_click"),
    AD_CLOSE("ad_close"),
    AD_OPEN("ad_shown")
}