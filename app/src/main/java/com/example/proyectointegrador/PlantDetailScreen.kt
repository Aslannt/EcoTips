package com.example.proyectointegrador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailScreen(navController: NavController, plantName: String, plantDescription: String, imageRes: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = plantName, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO: Acción de favorito*/ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_favorite_border), contentDescription = "Favorito")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Descripción",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            Text(
                text = plantDescription,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                PlantInfoDetail("Tipo", "Cactus")
                Spacer(modifier = Modifier.width(16.dp))
                PlantInfoDetail("Tamaño", "Medio")
                Spacer(modifier = Modifier.width(16.dp))
                PlantInfoDetail("Nivel", "Fácil")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Plantas Similares",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                SimilarPlantCard("Cactus más difíciles", R.drawable.round_cactus)
                Spacer(modifier = Modifier.width(16.dp))
                SimilarPlantCard("Cactus rojo", R.drawable.small_cactus)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /*TODO: Acción de plantar*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(text = "A Plantar!", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    }
}


@Composable
fun SimilarPlantCard(title: String, imageRes: Int) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Composable
fun PlantInfoDetail(title: String, value: String) {
    Column {
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Text(text = value, fontSize = 16.sp)
    }
}

