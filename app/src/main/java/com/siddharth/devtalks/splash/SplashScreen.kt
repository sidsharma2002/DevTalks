package com.siddharth.devtalks.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.navigation.NavHostController
import com.siddharth.devtalks.LocalNavHostController
import com.siddharth.devtalks.controllers.UserController
import com.siddharth.devtalks.navigation.NavRoute
import kotlinx.coroutines.delay

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SplashScreen(
    orgJsonString: String?,
    navController: NavHostController = LocalNavHostController.current
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LaunchedEffect(key1 = true) {
            if (UserController.getUserIfLoggedIn() != null) {
                delay(600L)
                navigateToHomeScreen(navController)
            } else {
                delay(600L)
                navigateToLoginScreen(navController)
            }
        }

        Text(
            text = "DEVOVERT",
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(25f, TextUnitType.Sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun navigateToHomeScreen(navController: NavHostController) {
    navController.navigate(NavRoute.HomeScreen.route) {
        popUpTo(NavRoute.Splash.route) { inclusive = true }
        launchSingleTop = true
    }
}

fun navigateToLoginScreen(navController: NavHostController) {
    navController.navigate(NavRoute.AuthScreen.route) {
        popUpTo(NavRoute.Splash.route) { inclusive = true }
        launchSingleTop = true
    }
}
