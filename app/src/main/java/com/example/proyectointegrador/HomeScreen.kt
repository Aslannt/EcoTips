package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                itemsIndexed(listOf("Tips", "Cuidados", "Calendario")) { index, _ ->
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

        items(plantsList) { plant ->
            PlantCard(plant, onClick = {
                val plantName = plant.name.urlEncode()
                val plantDescription = plant.description.urlEncode()
                val imageRes = plant.imageRes
                navController.navigate("plantDetail/$plantName/$plantDescription/$imageRes")
            })
        }
    }
}

@Composable
fun CategoryIcon(index: Int, navController: NavController) {
    val images = listOf(R.drawable.ic_tips, R.drawable.ic_care, R.drawable.ic_calendar)
    val descriptions = listOf("Tips", "Cuidados", "Calendario")

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
            .height(250.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
    ) {
        Column {
            Image(
                painter = painterResource(id = plant.imageRes),
                contentDescription = plant.name,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
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
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}
