package com.example.proyectointegrador

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ReminderItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, // Asegurar alineación vertical
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = Color.Green)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = if (checked) {
                TextStyle(textDecoration = TextDecoration.LineThrough)
            } else {
                TextStyle()
            }
        )
    }
}
