package com.jumpyjacko.slashg.ui

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
        composable(Screen.Calculation.route) {
            CalculationView(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsView(navController = navController)
        }
    }
}