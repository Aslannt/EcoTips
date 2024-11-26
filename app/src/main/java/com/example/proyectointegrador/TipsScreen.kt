package com.example.proyectointegrador

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import android.util.Log
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TipsScreen(navController: NavController) {
    var tips by remember { mutableStateOf(listOf<Tips>()) }
    var currentTipIndex by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(true) } // Añadimos un estado para el indicador de carga
    var displayedTips by remember { mutableStateOf(listOf<Tips>()) } // Estado para los tips mostrados

    LaunchedEffect(Unit) {
        FirebaseDatabase.getInstance().reference.child("tips")
            .get().addOnSuccessListener { snapshot ->
                val tipsList = mutableListOf<Tips>()
                snapshot.children.forEach { childSnapshot ->
                    childSnapshot.children.forEach { tipSnapshot ->
                        val tip = tipSnapshot.getValue(Tips::class.java)
                        if (tip != null) {
                            tipsList.add(tip)
                        } else {
                            Log.e("TipsScreen", "Invalid tip data: ${tipSnapshot.value}")
                        }
                    }
                }
                tips = tipsList
                loading = false // Ocultar el indicador de carga cuando los datos se cargan
            }
            .addOnFailureListener {
                loading = false // Ocultar el indicador de carga en caso de error
                Log.e("TipsScreen", "Error retrieving tips: ${it.message}")
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tips",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (loading) {
            // Mostrar indicador de carga mientras se cargan los datos
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false) // Permite que la columna ocupe espacio disponible sin empujar el botón fuera de la pantalla
                    .padding(bottom = 16.dp)
            ) {
                items(displayedTips) { tip ->
                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = tip.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50), // Verde
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = tip.description,
                            fontSize = 16.sp,
                        )
                    }
                }
            }

            if (currentTipIndex < tips.size) {
                Button(
                    onClick = {
                        if (tips.isNotEmpty()) {
                            val nextTip = tips[currentTipIndex]
                            displayedTips = displayedTips + nextTip // Añadir el nuevo tip a la lista de tips mostrados
                            currentTipIndex++ // Incrementar el índice sin ciclar
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Cambiar el color del botón
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Recibir nuevo consejo", color = Color.White) // Color del texto del botón
                }
            } else {
                Text(
                    text = "No hay más tips disponibles por ahora.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
                )
            }
        }
    }
}

data class Tips(
    val title: String = "",
    val description: String = ""
)
