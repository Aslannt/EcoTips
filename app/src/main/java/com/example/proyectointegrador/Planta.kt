package com.example.proyectointegrador

import com.example.proyectointegrador.R

// Clase Planta con las propiedades necesarias
data class Planta(
    val name: String, // Nombre de la planta
    val imageRes: Int, // Recurso de la imagen (ID de drawable)
    val description: String, // Descripción de la planta
    val dificultad: String // Dificultad de cuidado de la planta
)

// Lista de plantas que se usará en la UI
val plantsList = listOf(
    Planta("Cactus Circulo", R.drawable.round_cactus, "Un cactus redondo ideal para interiores.", "Fácil"),
    Planta("Cactus Pequeño", R.drawable.small_cactus, "Perfecto para escritorios o espacios pequeños.", "Fácil"),
    Planta("Planta Savila", R.drawable.planta_savila, "Sábila, conocida por sus propiedades medicinales.", "Medio"),
    Planta("Planta Interior", R.drawable.planta_interior, "Ideal para decorar interiores con estilo.", "Difícil")
)
