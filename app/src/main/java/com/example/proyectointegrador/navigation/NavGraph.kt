package com.example.proyectointegrador.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.proyectointegrador.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home/{username}") { backStackEntry ->
            HomeScreen(navController, userName = backStackEntry.arguments?.getString("username") ?: "Usuario")
        }
        composable(
            route = "plantDetail/{plantName}/{plantDescription}/{imageRes}",
            arguments = listOf(
                navArgument("plantName") { type = NavType.StringType },
                navArgument("plantDescription") { type = NavType.StringType },
                navArgument("imageRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val plantName = backStackEntry.arguments?.getString("plantName") ?: "Planta"
            val plantDescription = backStackEntry.arguments?.getString("plantDescription")?.urlDecode() ?: "Descripción"
            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.logo_app
            PlantDetailScreen(navController, plantName, plantDescription, imageRes)
        }
        composable("tips") { TipsScreen(navController) }
        composable("calendar") { CalendarScreen(navController) }
        composable("care") { CareScreen(navController) }
        composable("fertilizerDetail") { FertilizerDetailScreen(navController) }


    }
}
