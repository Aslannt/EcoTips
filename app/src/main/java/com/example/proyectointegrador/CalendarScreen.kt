package com.example.proyectointegrador

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val currentMonth = selectedDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val currentYear = selectedDate.year

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = "Calendario",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "$currentMonth $currentYear",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        CalendarView(
            selectedDate = selectedDate,
            onDateSelected = { newDate -> selectedDate = newDate }
        )

        Text(
            text = "Mis recordatorios",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn {
            item {
                ReminderItem("Regar planta de la cocina.")
                ReminderItem("Limpiar cactus de mi ventana.")
                ReminderItem("Comprar fertilizante.")
                ReminderItem("Seccionar mi basura reciclable.")
            }
            item {
                TextButton(onClick = { /* Lógica para añadir un nuevo recordatorio */ }) {
                    Text(text = "Añadir un nuevo recordatorio...")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val daysOfWeek = DayOfWeek.values()
    val yearMonth = YearMonth.of(selectedDate.year, selectedDate.month)
    val firstDayOfMonth = yearMonth.atDay(1)
    val lastDayOfMonth = yearMonth.atEndOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    // Lista de días con espacios en blanco antes del primer día del mes
    val daysInMonth = List(firstDayOfWeek) { null } + (1..lastDayOfMonth.dayOfMonth).toList()

    // Mostrar los días de la semana en una fila
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        for (day in daysOfWeek) {
            Text(
                text = day.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // Mostrar los días del mes en una cuadrícula
    Column {
        daysInMonth.chunked(7).forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                for (day in week) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(
                                if (day != null && day == selectedDate.dayOfMonth) Color(0xFF388E3C) else Color.Transparent
                            )
                            .clickable {
                                if (day != null) {
                                    onDateSelected(LocalDate.of(yearMonth.year, yearMonth.month, day))
                                }
                            }
                    ) {
                        if (day != null) {
                            Text(
                                text = day.toString(),
                                fontSize = 18.sp,
                                color = if (day == selectedDate.dayOfMonth) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReminderItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = { /* Actualizar estado del recordatorio */ }
        )
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
