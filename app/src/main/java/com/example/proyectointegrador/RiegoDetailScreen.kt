package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun RiegoDetailScreen(navController: NavController) {
    var currentRiegoIndex by remember { mutableStateOf(0) }
    val riegoTools = listOf(
        RiegoTool(
            name = "Regadera",
            description = "Una regadera es un recipiente portátil diseñado para regar plantas a mano. Tiene un pico largo y una rosa al final para distribuir el agua de manera uniforme.",
            imageRes = R.drawable.watering_can
        ),
        RiegoTool(
            name = "Manguera de Jardín",
            description = "Una manguera de jardín es un tubo flexible utilizado para llevar agua a plantas y céspedes. Generalmente está equipado con una boquilla ajustable para controlar el flujo de agua.",
            imageRes = R.drawable.garden_hose
        ),
        RiegoTool(
            name = "Rociador de Mano",
            description = "Un rociador de mano es una herramienta pequeña y portátil que se utiliza para rociar agua o soluciones fertilizantes sobre las plantas. Es ideal para aplicaciones precisas.",
            imageRes = R.drawable.hand_sprayer
        )
    )

    val currentRiegoTool = riegoTools[currentRiegoIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = currentRiegoTool.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Image(
            painter = painterResource(id = currentRiegoTool.imageRes),
            contentDescription = currentRiegoTool.name,
            modifier = Modifier
                .height(450.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = currentRiegoTool.description,
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
                    currentRiegoIndex = (currentRiegoIndex - 1 + riegoTools.size) % riegoTools.size
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2D5523)
                )
            ) {
                Text(text = "<", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Button(
                onClick = {
                    currentRiegoIndex = (currentRiegoIndex + 1) % riegoTools.size
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

data class RiegoTool(val name: String, val description: String, val imageRes: Int)
