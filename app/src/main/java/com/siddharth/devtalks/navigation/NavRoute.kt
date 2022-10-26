package com.siddharth.devtalks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import com.siddharth.devtalks.auth.AuthScreen
import com.siddharth.devtalks.home.HomeScreen
import com.siddharth.devtalks.splash.SplashScreen

sealed class NavRoute {

    abstract val content: @Composable (NavBackStackEntry) -> Unit
    abstract val route: String
    open val arguments: List<NamedNavArgument> = emptyList()
    open val deepLinks: List<NavDeepLink> = emptyList()

    object Splash : NavRoute() {
        override val content: @Composable (NavBackStackEntry) -> Unit = {
            SplashScreen(it.arguments?.getString("orgJson"))
        }
        override val route: String = "splash?params={orgJson}"
    }

    object HomeScreen : NavRoute() {
        override val content: @Composable (NavBackStackEntry) -> Unit = {
            HomeScreen(it.arguments?.getString("orgJson"))
        }
        override val route: String = "home"
    }

    object AuthScreen : NavRoute() {
        override val content: @Composable (NavBackStackEntry) -> Unit = {
            AuthScreen()
        }
        override val route: String
            get() = "auth"
    }
}

// Navigation uses '/' to denote params or nesting. Having a param that contains '/'
// will break the navigation. These replace '/' with '*' while navigating
private fun String?.toSafeNavUrl(): String = this?.replace('/', '*') ?: ""
private fun String?.fromSafeNavUrl(): String = this?.replace('*', '/') ?: ""
