package com.example.eco_controll_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eco_controll_mobile.ui.features.auth.LoginScreen
import com.example.eco_controll_mobile.ui.features.auth.SplashScreen
import com.example.eco_controll_mobile.ui.features.dashboard.HomeScreen
import com.example.eco_controll_mobile.ui.features.profile.SettingsScreen
import com.example.eco_controll_mobile.ui.features.profile.UserProfileScreen
import com.example.eco_controll_mobile.ui.features.registration.CisternRegistrationScreen
import com.example.eco_controll_mobile.ui.features.registration.SolarRegistrationScreen
import com.example.eco_controll_mobile.ui.features.reports.ReportsScreen
import com.example.eco_controll_mobile.ui.features.resources.CisternHistoryScreen
import com.example.eco_controll_mobile.ui.features.resources.ManageCisternScreen
import com.example.eco_controll_mobile.ui.features.resources.ManageSolarScreen
import com.example.eco_controll_mobile.ui.features.resources.SolarHistoryScreen

@Composable
fun EcoControllApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("login") { popUpTo("splash") { inclusive = true } }
            })
        }

        composable("login") {
            LoginScreen(onLoginClick = { navController.navigate("home") })
        }

        composable("home") {
            HomeScreen(
                onNavigateToManageCistern = { navController.navigate("manage_cistern") },
                onNavigateToHistoryCistern = { navController.navigate("history_cistern") },
                onNavigateToManageSolar = { navController.navigate("manage_solar") },
                onNavigateToHistorySolar = { navController.navigate("history_solar") },
                onNavigateToSettings = { navController.navigate("settings") },
                onNavigateToReports = { navController.navigate("reports") },
                onNavigateToProfile = { navController.navigate("profile") }
            )
        }

        // Rotas com botão de voltar funcionando
        composable("manage_cistern") { ManageCisternScreen { navController.popBackStack() } }

        // 1. Rota da Cisterna (Leva para o cadastro da Cisterna)
        composable(route = "history_cistern") {
            CisternHistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRegistration = { navController.navigate("register_cistern") }
            )
        }

        composable("manage_solar") { ManageSolarScreen { navController.popBackStack() } }

        // 2. Rota do Solar (Leva para o cadastro do Solar)
        composable(route = "history_solar") {
            SolarHistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRegistration = { navController.navigate("register_solar") }
            )
        }

        // 3. Telas de Cadastro Independentes
        composable(route = "register_cistern") {
            CisternRegistrationScreen(onBackClick = { navController.popBackStack() })
        }

        composable(route = "register_solar") {
            SolarRegistrationScreen(onBackClick = { navController.popBackStack() })
        }
        composable("settings") { SettingsScreen { navController.popBackStack() } }
        composable("profile") { UserProfileScreen { navController.popBackStack() } }
        composable("reports") { ReportsScreen { navController.popBackStack() } }
    }
}