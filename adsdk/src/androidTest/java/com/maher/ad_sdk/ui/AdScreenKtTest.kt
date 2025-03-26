package com.maher.ad_sdk.ui

import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.Coil
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.ui.theme.AdSdkTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class AdScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeAdModel: AdModel

    @OptIn(ExperimentalCoilApi::class)
    @Before
    fun setup() {

        val engine = FakeImageLoaderEngine.Builder()
            .intercept("https://example.com/ad.jpg", ColorDrawable())
            .default(ColorDrawable())
            .build()
        val imageLoader = ImageLoader.Builder(ApplicationProvider.getApplicationContext())
            .components { add(engine) }
            .build()
        Coil.setImageLoader(imageLoader)

        fakeAdModel = AdModel(
            static = "https://example.com/ad.jpg",
            closeDelay = 4,
            clickThrough = "https://example.com/click",
            tracking = "https://example.com/tracking"
        )
    }

    @Test
    fun testAdScreenClick() {
        var clicked = false

        composeTestRule.setContent {
            AdSdkTheme {
                AdScreen(
                    adModel = fakeAdModel,
                    onClickCallback = { clicked = true },
                    onCloseCallback = {}
                )
            }
        }

        // Perform click on the ad
        composeTestRule.onRoot().performClick()

        // Assert that the click callback was invoked
        assert(clicked)
    }

    @Test
    fun testAdScreenCloseButtonVisible() = runTest {
        composeTestRule.setContent {
            AdSdkTheme {
                AdScreen(
                    adModel = fakeAdModel,
                    onClickCallback = {},
                    onCloseCallback = {}
                )
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.mainClock.advanceTimeBy(fakeAdModel.closeDelay * 1000L)

        // Assert that the close button is visible
        composeTestRule.onNodeWithText("Close").assertIsDisplayed()
    }

    @Test
    fun testAdScreenCloseButtonClick() = runTest {
        var closed = false

        composeTestRule.setContent {
            AdSdkTheme {
                AdScreen(
                    adModel = fakeAdModel,
                    onClickCallback = {},
                    onCloseCallback = { closed = true }
                )
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.mainClock.advanceTimeBy(fakeAdModel.closeDelay * 1000L)

        // Perform click on the close button
        composeTestRule.onNodeWithText("Close").performClick()

        // Assert that the close callback was invoked
        assert(closed)
    }
}