package com.jumpyjacko.slashg.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Calculation : Screen("home")
    object Settings : Screen("settings")
}

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Calculation.route,
    content: NavGraphBuilder.() -> Unit,
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = content,
    )
}

@Composable
fun NavigationGraphContent(navController: NavHostController) {
    NavigationGraph(navController = navController) {
        composable(
            route = Screen.Calculation.route,
        ) {
            CalculationView(navController = navController)
        }
        composable(
            route = Screen.Settings.route,
            enterTransition = {
                scaleIn(
                    animationSpec = tween(durationMillis = 220, delayMillis = 90),
                    initialScale = 1.1f
                ) + fadeIn(animationSpec = tween(durationMillis = 220, delayMillis = 90))
            },
            exitTransition = {
                scaleOut(
                    animationSpec = tween(durationMillis = 220, delayMillis = 90),
                    targetScale = 1.1f
                ) + fadeOut(animationSpec = tween(durationMillis = 220, delayMillis = 90))
            },
        ) {
            SettingsView(navController = navController)
        }
    }
}