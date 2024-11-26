package com.example.proyectointegrador

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.security.MessageDigest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, auth: FirebaseAuth) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.plants_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Se uno con tu Ambiente!",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario", color = Color.White) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF81C784), // Verde medio claro
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.Black, // Texto ingresado por el usuario (negro)
                    unfocusedTextColor = Color.Black, // Texto ingresado (negro)
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico", color = Color.White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF81C784), // Verde medio claro
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.Black, // Texto ingresado por el usuario (negro)
                    unfocusedTextColor = Color.Black, // Texto ingresado (negro)
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF81C784), // Verde medio claro
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.Black, // Texto ingresado por el usuario (negro)
                    unfocusedTextColor = Color.Black, // Texto ingresado (negro)
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                                val encryptedPassword = encryptPassword(password)

                                val user = mapOf(
                                    "username" to username,
                                    "email" to email,
                                    "password" to encryptedPassword
                                )

                                FirebaseDatabase.getInstance().reference.child("users").child(userId)
                                    .setValue(user)
                                    .addOnCompleteListener {
                                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                        navController.navigate("login")
                                    }
                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)), // Verde oscuro
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar", color = Color.White)
            }
        }
    }
}

fun encryptPassword(password: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val bytes = password.toByteArray()
    val digest = messageDigest.digest(bytes)
    return digest.joinToString("") { "%02x".format(it) }
}
