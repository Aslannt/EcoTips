package com.example.proyectointegrador

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddReminderDialog(onDismiss: () -> Unit, onAddReminder: (String) -> Unit) {
    var reminderText by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) } // Indica si hay un error

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Añadir un nuevo recordatorio",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextField(
                    value = reminderText,
                    onValueChange = {
                        reminderText = it
                        error = false // Resetear el error al escribir
                    },
                    label = { Text("Descripción del recordatorio") },
                    isError = error, // Mostrar error si es necesario
                    modifier = Modifier.fillMaxWidth()
                )

                if (error) {
                    Text(
                        text = "El recordatorio no puede estar vacío.",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Cancelar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (reminderText.isBlank()) {
                            error = true // Mostrar error si está vacío
                        } else {
                            onAddReminder(reminderText)
                            onDismiss()
                        }
                    }) {
                        Text(text = "Añadir", color = Color.White)
                    }
                }
            }
        }
    }
}
