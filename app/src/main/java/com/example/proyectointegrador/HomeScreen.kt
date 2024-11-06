package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.net.URLEncoder
import java.net.URLDecoder

fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")
fun String.urlDecode(): String = URLDecoder.decode(this, "utf-8")


@Composable
fun HomeScreen(navController: NavController, userName: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Bienvenido, $userName",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
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

            LazyRow(modifier = Modifier.padding(bottom = 16.dp)) {
                items(4) { index ->
                    CategoryIcon(index, navController)
                }
            }

            Text(
                text = "Plantas populares",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(5) {
            PlantCard(
                Planta("Cactus Circulo", R.drawable.round_cactus),
                onClick = {
                    val plantName = "Cactus Circulo".urlEncode()
                    val plantDescription = "Descripción del Cactus Circulo".urlEncode()
                    val imageRes = R.drawable.round_cactus
                    navController.navigate("plantDetail/$plantName/$plantDescription/$imageRes")
                }
            )
        }
    }
}

@Composable
fun CategoryIcon(index: Int, navController: NavController) {
    val images = listOf(R.drawable.ic_tips, R.drawable.ic_plant_with_me, R.drawable.ic_care, R.drawable.ic_calendar)
    val descriptions = listOf("Tips", "Planta Conmigo", "Cuidados", "Calendario")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                when (descriptions[index]) {
                    "Tips" -> navController.navigate("tips")
                    "Cuidados" -> navController.navigate("care")
                    "Calendario" -> navController.navigate("calendar")
                }
            }
    ) {
        Image(
            painter = painterResource(id = images[index]),
            contentDescription = descriptions[index],
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = descriptions[index], fontSize = 14.sp)
    }
}


@Composable
fun PlantCard(plant: Planta, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(250.dp),
        onClick = onClick
    ) {
        Column {
            Image(
                painter = painterResource(id = plant.imageRes),
                contentDescription = plant.name,
                modifier = Modifier.height(150.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = plant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Ver más",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}

