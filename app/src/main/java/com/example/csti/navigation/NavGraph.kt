package com.example.csti.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.csti.view.LoginScreen
import com.example.csti.view.MainScreen
import com.example.csti.view.MainScreenAdmin
import com.example.csti.view.MisReportesAdmin

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("MainScreen") { MainScreen(navController) }
        composable("MainScreenAdmin") { MainScreenAdmin(navController) }
        composable("misReportesAdmin") { MisReportesAdmin(navController) }
        composable("detalle/{idSolicitud}?fromMisReportes={fromMisReportes}") { backStackEntry ->
            val idSolicitud = backStackEntry.arguments?.getString("idSolicitud") ?: return@composable
            val fromMisReportes = backStackEntry.arguments?.getString("fromMisReportes")?.toBoolean() ?: false
        }
        composable("detalleAdmin/{idSolicitud}") { backStackEntry ->
            val idSolicitud = backStackEntry.arguments?.getString("idSolicitud") ?: return@composable
        }
    }
}
