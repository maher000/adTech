package com.maher.ad_sdk

import com.maher.ad_sdk.common.AdSdkResponse
import com.maher.ad_sdk.data.AdSdkImpl
import com.maher.ad_sdk.data.NetworkModule
import com.maher.ad_sdk.di.AdSdkModule
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AdSdkInstanceTest {

    private val fakeAdService = FakeAdService()

    @get: Rule
    @ExperimentalCoroutinesApi
    var coroutinesTestRule = CoroutinesTestRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        NetworkModule.adService = fakeAdService
        AdSdkModule.adSdk = AdSdkImpl(
            dispatcher = coroutinesTestRule.testDispatcher,
            adService = NetworkModule.adService
        )
    }

    @Test
    fun testLoadSuccess() = runTest {
        val response: AdSdkResponse<AdModel> = AdSdkInstance.load()

        with(FakeAdService.fakeAdResponse) {
            assert(response is AdSdkResponse.Success)
            response as AdSdkResponse.Success

            assertEquals(clickThrough, response.data.clickThrough)
            assertEquals(static, response.data.static)
            assertEquals(closeDelay, response.data.closeDelay)
            assertEquals(tracking, response.data.tracking)
        }
    }

    @Test
    fun testLoadError() = runTest {
        FakeAdService.state = FakeAdService.State.ERROR
        val response: AdSdkResponse<AdModel> = AdSdkInstance.load()
        assert(response is AdSdkResponse.Error)
    }
}