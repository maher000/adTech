package com.maher.adtech

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "main",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                composable("main") {
                    MainScreen()
                }
            }
        }
    )
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}
