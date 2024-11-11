package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun ToolsDetailScreen(navController: NavController) {
    var currentToolIndex by remember { mutableStateOf(0) }
    val tools = listOf(
        Tool(
            name = "Horquilla de Mano",
            description = "Una horquilla de mano es una herramienta pequeña de jardinería con tres o cuatro púas. Se usa para aflojar, levantar y voltear la tierra en jardinería y agricultura.",
            imageRes = R.drawable.hand_fork
        ),
        Tool(
            name = "Paleta de Mano",
            description = "Una paleta es una herramienta pequeña con una hoja de metal en forma de pala y un mango. Se usa para cavar pequeños agujeros, trasplantar plántulas y otras tareas de jardinería a pequeña escala.",
            imageRes = R.drawable.trowel
        ),
        Tool(
            name = "Cultivador de Mano",
            description = "Un cultivador de mano es una herramienta de jardinería con tres o más dientes que se usa para romper el suelo, eliminar malezas y airear la tierra. Es ideal para preparar pequeñas parcelas de jardín y mantener la salud del suelo.",
            imageRes = R.drawable.cultivator
        )
    )

    val currentTool = tools[currentToolIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = currentTool.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Image(
            painter = painterResource(id = currentTool.imageRes),
            contentDescription = currentTool.name,
            modifier = Modifier
                .height(450.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = currentTool.description,
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
                    currentToolIndex = (currentToolIndex - 1 + tools.size) % tools.size
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2D5523)
                )
            ) {
                Text(text = "<", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Button(
                onClick = {
                    currentToolIndex = (currentToolIndex + 1) % tools.size
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

data class Tool(val name: String, val description: String, val imageRes: Int)
