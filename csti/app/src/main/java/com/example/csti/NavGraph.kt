package com.example.csti

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.csti.data.solicitud

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("MainScreen") { MainScreen(navController) }
        composable("MainScreenAdmin") { MainScreenAdmin(navController) }
        composable("misReportes") { MisReportesScreen(navController) }
        composable("misReportesAdmin") { MisReportesAdmin(navController) }
        composable("detalle/{idSolicitud}?fromMisReportes={fromMisReportes}") { backStackEntry ->
            val idSolicitud = backStackEntry.arguments?.getString("idSolicitud")?.toInt() ?: return@composable
            val fromMisReportes = backStackEntry.arguments?.getString("fromMisReportes")?.toBoolean() ?: false
            DetalleReporteScreen(navController, idSolicitud, fromMisReportes)
        }
        composable("detalleAdmin/{idSolicitud}") { backStackEntry ->
            val idSolicitud = backStackEntry.arguments?.getString("idSolicitud")?.toInt() ?: return@composable
            DetalleReporteAdminScreen(navController, idSolicitud)
        }
    }
}
