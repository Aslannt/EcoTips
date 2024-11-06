package com.example.proyectointegrador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.proyectointegrador.navigation.NavGraph
import com.example.proyectointegrador.ui.theme.ProyectoIntegradorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoIntegradorTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
