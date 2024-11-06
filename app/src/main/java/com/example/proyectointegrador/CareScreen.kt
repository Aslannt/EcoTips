package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CareScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = "Cuidados",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CareItem(
            title = "FERTILIZANTE",
            imageRes = R.drawable.fertilizante, // Reemplaza con la imagen adecuada
            onClick = { navController.navigate("fertilizerDetail") }
        )

        CareItem(
            title = "HERRAMIENTAS",
            imageRes = R.drawable.herramientas, // Reemplaza con la imagen adecuada
            onClick = { /* Navegación o lógica al hacer clic en HERRAMIENTAS */ }
        )

        CareItem(
            title = "RIEGO",
            imageRes = R.drawable.logo_app, // Reemplaza con la imagen adecuada
            onClick = { /* Navegación o lógica al hacer clic en RIEGO */ }
        )
    }
}

@Composable
fun CareItem(title: String, imageRes: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
