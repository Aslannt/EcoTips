package com.example.proyectointegrador

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.proyectointegrador.navigation.NavGraph
import com.example.proyectointegrador.ui.theme.ProyectoIntegradorTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContent {
            ProyectoIntegradorTheme {
                val navController = rememberNavController()

                // Llamar a NavGraph para definir las rutas
                NavGraph(navController = navController, auth = auth)

                // Verificar si el usuario ya está autenticado y redirigir apropiadamente
                auth.currentUser?.let {
                    navController.navigate("home/${it.email}")
                } ?: run {
                    navController.navigate("login")
                }
            }
        }
    }
}
