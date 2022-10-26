package com.siddharth.devtalks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.siddharth.devtalks.others.ViewModelFactory
import com.siddharth.devtalks.ui.theme.DevTalksTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModelFactory: ViewModelFactory by inject()

    @OptIn(ExperimentalComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevTalksTheme {
                Surface(Modifier.fillMaxSize()) {
                    Root(viewModelFactory)
                }
            }
        }
    }
}

val LocalNavHostController =
    compositionLocalOf<NavHostController> { error { "No NavHostController found!" } }
val LocalViewModelFactory =
    compositionLocalOf<ViewModelFactory> { error { "No ViewModel Factory found" } }

@ExperimentalComposeApi
@Composable
fun Root(viewModelFactory: ViewModelFactory) {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavHostController provides navController,
        LocalViewModelFactory provides viewModelFactory
    ) {
        Host()
    }
}
