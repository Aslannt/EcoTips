package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@Composable
fun FertilizerDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = "Nitrorganic",
            fontSize = 24.sp, // Tamaño de la letra reducido
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally) // Texto centrado
        )

        Image(
            painter = painterResource(id = R.drawable.fertilizer_nitrorganic), // Reemplaza con la imagen adecuada
            contentDescription = "Nitrorganic",
            modifier = Modifier
                .height(450.dp) // Ajuste de la altura para que se vea más alargada
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para empujar la descripcion hacia abajo

        Text(
            text = "Fertilizante técnicamente granulado con aporte de nitrógeno orgánico y minerales; fundamental en la síntesis de proteínas para alcanzar mayores rendimientos sin afectar el medio ambiente.",
            fontSize = 16.sp, // Tamaño de la letra reducido
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp) // Ajustar el espaciado aquí
                .align(Alignment.CenterHorizontally) // Texto centrado
        )

        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para empujar el botón hacia abajo

        Button(
            onClick = { /* Lógica para agregar a favoritos */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D5523) // Color verde oscuro
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.CenterHorizontally) // Botón centrado
        ) {
            Text(text = "Agregar a favoritos", color = Color.White) // Cambiar el color del texto a blanco
        }
    }
}
