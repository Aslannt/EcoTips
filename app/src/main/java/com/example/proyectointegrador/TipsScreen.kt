package com.example.proyectointegrador

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(navController: NavController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Cambiar fondo a blanco
                .padding(16.dp)
        ) {
            // Título similar al de "EcoTips" en HomeScreen
            Text(
                text = "Tips",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // Cambiar color a negro
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Lista de tips
            val tips = listOf(
                "Recuerda regar tus plantas",
                "Usa materiales reciclados para los maceteros y soportes",
                "Elige un lugar soleado pero protegido del viento para tu planta",
                "Proporciona suficiente espacio entre las plantas para que crezcan correctamente"
            )

            tips.forEach { tip ->
                TipCard(tip = tip, backgroundColor = Color(0xFF2D5523)) // Cambiar el color de fondo
            }
        }
    }
}

@Composable
fun TipCard(tip: String, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor), // Usar el color de fondo especificado
        shape = RoundedCornerShape(16.dp) // Hacer las esquinas más redondeadas
    ) {
        Text(
            text = tip,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(16.dp)
        )
    }
}
