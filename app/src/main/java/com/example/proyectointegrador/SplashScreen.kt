package com.example.proyectointegrador

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectointegrador.ui.theme.ProyectoIntegradorTheme

@Composable
fun SplashScreen(navController: NavController) {
    Handler(Looper.getMainLooper()).postDelayed({
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }, 3000)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2D5523)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo de la Aplicación",
                modifier = Modifier.size(128.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Consejos Verdes Diarios",
                style = MaterialTheme.typography.headlineLarge, // Usar tipografía personalizada
                color = Color.White
            )
        }
    }
}