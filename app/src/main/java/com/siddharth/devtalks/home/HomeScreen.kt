package com.siddharth.devtalks.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.siddharth.devtalks.LocalNavHostController

@Composable
fun HomeScreen(
    orgJsonString: String?,
    navController: NavHostController = LocalNavHostController.current
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "HomeScreen", modifier = Modifier.align(Alignment.Center))
    }

}