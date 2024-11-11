package com.example.proyectointegrador

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectointegrador.ui.theme.ProyectoIntegradorTheme
import java.util.regex.Pattern

@Composable
fun LoginScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var showButton by remember { mutableStateOf(false) }
    var showInvalidEmailDialog by remember { mutableStateOf(false) }

    val isEmailValid = remember(email) {
        email.contains("@") && Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        ).matcher(email).matches()
    }

    LaunchedEffect(name, email, password) {
        showButton = name.isNotEmpty() && isEmailValid && password.text.length >= 6
        if (!isEmailValid && email.isNotEmpty()) {
            showInvalidEmailDialog = true
        } else {
            showInvalidEmailDialog = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.plant_image),
            contentDescription = "Fondo de pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Se uno con tu Ambiente!",
                style = MaterialTheme.typography.headlineLarge, // Usar tipografía personalizada
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Ingresa tú usuario", color = Color.White) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x802E7D32),
                    unfocusedContainerColor = Color(0x802E7D32),
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0x802E7D32), RoundedCornerShape(24.dp))
                    .animateContentSize()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "Ingresar correo", color = Color.White) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x802E7D32),
                    unfocusedContainerColor = Color(0x802E7D32),
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                isError = !isEmailValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0x802E7D32), RoundedCornerShape(24.dp))
                    .animateContentSize()
            )

            if (showInvalidEmailDialog) {
                Text(
                    text = "Por favor ingresa un correo válido.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Ingresar contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x802E7D32),
                    unfocusedContainerColor = Color(0x802E7D32),
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0x802E7D32), RoundedCornerShape(24.dp))
                    .animateContentSize()
            )

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(animationSpec = tween(1000)) + expandVertically(),
                exit = fadeOut(animationSpec = tween(500)) + shrinkVertically()
            ) {
                Button(
                    onClick = {
                        navController.navigate("home/$name")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            BorderStroke(2.dp, Color.White),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Ingresar",
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProyectoIntegradorTheme {
        LoginScreen(navController = rememberNavController())
    }
}
