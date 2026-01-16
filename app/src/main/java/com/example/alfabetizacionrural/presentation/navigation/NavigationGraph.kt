package com.example.alfabetizacionrural.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alfabetizacionrural.presentation.RegistrationScreen
import com.example.alfabetizacionrural.presentation.dashboard.MainScreen

object AppDestinations {
    const val REGISTRATION = "registration"
    const val MAIN_DASHBOARD = "main_dashboard"
}

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    startDestination: String,
    onFinish: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestinations.REGISTRATION) {
            RegistrationScreen(
                onRegistrationSuccess = {
                    navController.navigate(AppDestinations.MAIN_DASHBOARD) {
                        popUpTo(AppDestinations.REGISTRATION) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestinations.MAIN_DASHBOARD) {
            MainScreen()
        }
    }
}
