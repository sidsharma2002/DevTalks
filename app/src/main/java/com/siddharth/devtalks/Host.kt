package com.siddharth.devtalks

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.get
import com.siddharth.devtalks.navigation.NavRoute
import com.siddharth.devtalks.ui.theme.DevTalksTheme

@Composable
fun Host() {

    val navController = LocalNavHostController.current

    DevTalksTheme {
        NavHost(navController, startDestination = NavRoute.Splash.route) {
            listOf(
                NavRoute.Splash,
                NavRoute.AuthScreen,
                NavRoute.HomeScreen
            ).forEach { addNavRoute(it) }
        }
    }
}

fun NavGraphBuilder.addNavRoute(navRoute: NavRoute) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], navRoute.content).apply {
            this.route = navRoute.route
            navRoute.arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            navRoute.deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}