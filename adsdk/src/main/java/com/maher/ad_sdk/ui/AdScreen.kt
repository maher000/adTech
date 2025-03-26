package com.maher.ad_sdk.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maher.ad_sdk.domain.AdModel
import com.maher.ad_sdk.ui.theme.AdSdkTheme
import kotlinx.coroutines.delay

@Composable
fun AdScreen(
    adModel: AdModel,
    onClickCallback: () -> Unit,
    onCloseCallback: () -> Unit,
) {
    val showCloseButton = rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onClickCallback()
            }
    ) {

        AsyncImage(
            model = adModel.static,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
            )

        LaunchedEffect(Unit) {
            delay(adModel.closeDelay * 1000L)
            showCloseButton.value = true
        }

        if (showCloseButton.value) {
            Button(
                onClick = {
                    onCloseCallback()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text("Close")
            }
        }
    }
}

private val fakeAdModel = AdModel(
    static = "https://example.com/ad.jpg",
    closeDelay = 10,
    clickThrough = "https://example.com/click",
    tracking = "https://example.com/tracking"
)

@Preview
@Composable
fun AdScreenPreview() {
    AdSdkTheme {
        AdScreen(fakeAdModel, onClickCallback = {}, onCloseCallback = {})
    }
}