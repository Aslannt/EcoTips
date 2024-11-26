package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    var currentFertilizerIndex by remember { mutableStateOf(0) }
    val fertilizers = listOf(
        Fertilizer(
            name = "Nitrorganic",
            description = "Fertilizante técnicamente granulado con aporte de nitrógeno orgánico y minerales; fundamental en la síntesis de proteínas para alcanzar mayores rendimientos sin afectar el medio ambiente.",
            imageRes = R.drawable.fertilizer_nitrorganic
        ),
        Fertilizer(
            name = "Producción",
            description = "El Fertilizante Producción 17-6-18-2 x 50 Kg es una fórmula equilibrada diseñada para satisfacer las necesidades nutricionales de tus cultivos. Con una proporción de nutrientes de 17% de nitrógeno (N), 6% de fósforo (P), 18% de potasio (K) y 2% de azufre (S), este fertilizante promueve un crecimiento robusto y un desarrollo saludable de las plantas.",
            imageRes = R.drawable.fertilizer_2 // Reemplaza con la imagen adecuada
        ),
        Fertilizer(
            name = "Yara Mila",
            description = "YaraMila Rafos es un fertilizante granular con un alto contenido de fósforo que es especialmente necesario en etapas tempranas del cultivo para promover el desarrollo de raíces y el crecimiento de las plantas. También aporta nitrógeno, potasio, magnesio, azufre, boro y zinc en una relación óptima para el desarrollo en las primeras etapas del cultivo.",
            imageRes = R.drawable.fertilizer_3 // Reemplaza con la imagen adecuada
        )
    )

    val currentFertilizer = fertilizers[currentFertilizerIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = currentFertilizer.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Image(
            painter = painterResource(id = currentFertilizer.imageRes),
            contentDescription = currentFertilizer.name,
            modifier = Modifier
                .height(450.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = currentFertilizer.description,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentFertilizerIndex = (currentFertilizerIndex - 1 + fertilizers.size) % fertilizers.size
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2D5523)
                )
            ) {
                Text(text = "<", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Button(
                onClick = {
                    currentFertilizerIndex = (currentFertilizerIndex + 1) % fertilizers.size
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2D5523)
                )
            ) {
                Text(text = ">", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }

        Button(
            onClick = { /* Lógica para agregar a favoritos */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D5523)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Agregar a favoritos", color = Color.White)
        }
    }
}

data class Fertilizer(val name: String, val description: String, val imageRes: Int)
