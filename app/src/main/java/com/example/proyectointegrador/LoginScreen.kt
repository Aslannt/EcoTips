package com.example.proyectointegrador

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectointegrador.ui.theme.ProyectoIntegradorTheme
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

@Composable
fun LoginScreen(navController: NavController, auth: FirebaseAuth) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showButton by remember { mutableStateOf(false) }
    var showInvalidEmailDialog by remember { mutableStateOf(false) }
    var showInvalidPasswordDialog by remember { mutableStateOf(false) }
    var showIncorrectCredentialsError by remember { mutableStateOf(false) } // Error de credenciales incorrectas
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val isEmailValid = remember(email) {
        email.contains("@") && Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        ).matcher(email).matches()
    }

    LaunchedEffect(name, email, password) {
        showButton = name.isNotEmpty() && isEmailValid && password.length >= 6
        showInvalidEmailDialog = email.isNotEmpty() && !isEmailValid
        showInvalidPasswordDialog = password.isNotEmpty() && password.length < 6
        showIncorrectCredentialsError = false // Resetear error de credenciales al cambiar datos
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
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Campo de nombre de usuario
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Ingresa tu usuario", color = Color.White) },
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

            // Campo de correo electrónico
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "Ingresar correo", color = Color.White) },
                isError = showInvalidEmailDialog,
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

            // Mostrar mensaje de error si el correo no es válido
            if (showInvalidEmailDialog) {
                Text(
                    text = "Por favor ingresa un correo válido.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de contraseña
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Ingresar contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                isError = showInvalidPasswordDialog,
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

            // Mostrar mensaje de error si la contraseña es demasiado corta
            if (showInvalidPasswordDialog) {
                Text(
                    text = "La contraseña debe tener al menos 6 caracteres.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Mostrar error si las credenciales son incorrectas
            if (showIncorrectCredentialsError) {
                Text(
                    text = "Correo o contraseña incorrectos.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de "Ingresar"
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(animationSpec = tween(1000)) + expandVertically(),
                exit = fadeOut(animationSpec = tween(500)) + shrinkVertically()
            ) {
                Button(
                    onClick = {
                        // Intento de inicio de sesión con Firebase Auth
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Guardar el nombre del usuario en SharedPreferences
                                    sharedPreferences.edit().putString("user_name", name).apply()

                                    // Navegar a la pantalla de inicio si el inicio de sesión es exitoso
                                    navController.navigate("home/$name")
                                } else {
                                    // Mostrar mensaje de error si las credenciales son incorrectas
                                    showIncorrectCredentialsError = true
                                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                }
                            }
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

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para ir a la pantalla de registro
            TextButton(
                onClick = { navController.navigate("register") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("¿No tienes cuenta? Regístrate aquí", color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProyectoIntegradorTheme {
        val navController = rememberNavController()
        val auth = FirebaseAuth.getInstance()
        LoginScreen(navController = navController, auth = auth)
    }
}
