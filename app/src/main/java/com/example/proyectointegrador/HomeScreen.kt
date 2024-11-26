package com.example.proyectointegrador

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(navController: NavController, userName: String) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val savedUserName = sharedPreferences.getString("user_name", userName) ?: userName

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Mostrar nombre de usuario y opción para cerrar sesión
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Bienvenido, $savedUserName",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                // Botón para cerrar sesión
                Text(
                    text = "Cerrar sesión",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.clickable {
                        auth.signOut()
                        sharedPreferences.edit().clear().apply()
                        Toast.makeText(navController.context, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                        navController.navigate("login") {
                            popUpTo("home/$savedUserName") { inclusive = true }
                        }
                    }
                )
            }

            Text(
                text = "EcoTips",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Categorías",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(listOf("Tips", "Cuidados", "Calendario")) { category ->
                    CategoryIcon(category, navController)
                }
            }

            Text(
                text = "Plantas populares",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Aquí usamos la lista de plantas correctamente
        items(plantsList) { plant ->
            PlantCard(plant = plant, onClick = {
                val plantName = plant.name.urlEncode() // Codificamos el nombre
                val plantDescription = plant.description.urlEncode() // Codificamos la descripción
                val imageRes = plant.imageRes // Usamos el ID de imagen
                navController.navigate("plantDetail/$plantName/$plantDescription/$imageRes") // Navegamos a la pantalla de detalles
            })
        }
    }
}

@Composable
fun CategoryIcon(category: String, navController: NavController) {
    val images = mapOf(
        "Tips" to R.drawable.ic_tips,
        "Cuidados" to R.drawable.ic_care,
        "Calendario" to R.drawable.ic_calendar
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                when (category) {
                    "Tips" -> navController.navigate("tips")
                    "Cuidados" -> navController.navigate("care")
                    "Calendario" -> navController.navigate("calendar")
                }
            }
    ) {
        Image(
            painter = painterResource(id = images[category]!!),
            contentDescription = category,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = category, fontSize = 14.sp)
    }
}

@Composable
fun PlantCard(plant: Planta, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(250.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
    ) {
        Column {
            // Usamos correctamente la propiedad imageRes
            Image(
                painter = painterResource(id = plant.imageRes),
                contentDescription = plant.name,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            // Usamos correctamente las propiedades name y description
            Text(
                text = plant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = plant.description,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
            // Usamos correctamente la propiedad dificultad
            Text(
                text = "Dificultad: ${plant.dificultad}",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}

// Funciones para codificar y decodificar URLs
fun String.urlEncode(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
fun String.urlDecode(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
