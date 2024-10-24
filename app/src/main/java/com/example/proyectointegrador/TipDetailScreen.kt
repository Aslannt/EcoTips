package com.example.proyectointegrador

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TipDetailScreen(tip: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalles del Consejo",
            style = MaterialTheme.typography.headlineLarge, // Usar tipografía personalizada
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = tip,
            style = MaterialTheme.typography.bodyLarge // Usar tipografía personalizada
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipDetailPreview() {
    TipDetailScreen(tip = "Este es un consejo verde detallado.")
}
