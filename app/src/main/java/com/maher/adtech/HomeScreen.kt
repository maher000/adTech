package com.maher.adtech

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maher.ad_sdk.AdSdkInstance
import com.maher.ad_sdk.domain.AdModel


@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val loadAd = remember { mutableStateOf(false) }
    var adModel: AdModel? = null

    LaunchedEffect(loadAd) {
        adModel = AdSdkInstance.load()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            loadAd.value = !loadAd.value
        }) {
            Text("Load")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            adModel?.let {
                AdSdkInstance.show(context, it)
            }
        }) {
            Text("Show")
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    HomeScreen()
}
