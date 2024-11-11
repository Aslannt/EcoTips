package com.example.proyectointegrador

import com.example.proyectointegrador.R

data class Planta(
    val name: String,
    val imageRes: Int,
    val description: String,
    val dificultad: String
)

val plantsList = listOf(
    Planta("Cactus Circulo", R.drawable.round_cactus, "Un cactus redondo ideal para interiores.", "Fácil"),
    Planta("Cactus Pequeño", R.drawable.small_cactus, "Perfecto para escritorios o espacios pequeños.", "Fácil"),
    Planta("Planta Savila", R.drawable.planta_savila, "Sábila, conocida por sus propiedades medicinales.", "Medio"),
    Planta("Planta Interior", R.drawable.planta_interior, "Ideal para decorar interiores con estilo.", "Difícil")
)
