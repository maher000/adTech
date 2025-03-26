package com.maher.adtech

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maher.ad_sdk.AdSdkInstance
import com.maher.ad_sdk.common.AdSdkResponse
import com.maher.ad_sdk.domain.AdModel
import kotlinx.coroutines.launch


private const val TAG: String = "HomeScreen"

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    var adModel: AdModel? = null
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            coroutineScope.launch {
                when (val adResponse = AdSdkInstance.load()) {
                    is AdSdkResponse.Success -> {
                        adModel = adResponse.data
                    }

                    is AdSdkResponse.Error -> {
                        Log.d(TAG, adResponse.message)
                    }
                }
            }
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
