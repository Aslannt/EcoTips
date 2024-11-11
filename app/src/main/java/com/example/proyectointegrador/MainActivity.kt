package com.example.proyectointegrador

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.proyectointegrador.navigation.NavGraph
import com.example.proyectointegrador.ui.theme.ProyectoIntegradorTheme

// FIREBASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    // Inicialización de Firebase Auth y Database Reference
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance().reference
//        setContentView(R.layout.login_activity)
//        val btnEnviar: Button = findViewById(R.id.btnEnviar)

//        btnEnviar.setOnClickListener {

//            val email = findViewById<TextView>(R.id.input_documento).text.toString()
//            val password = findViewById<TextView>(R.id.input_contrasenia).text.toString()

//            if (email.isNotEmpty() && password.isNotEmpty()) {
//                // Validar usuario con Firebase Auth
//                auth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this) { task ->
//                        if (task.isSuccessful) {
//                            database.child("Permisos").get()
//                                .addOnSuccessListener { snapshot ->
//                                    var encontrado = false
//
//                                    // Buscar en el grupo "1"
//                                    val grupo1Snapshot = snapshot.child("1")
//                                    for (correoSnapshot in grupo1Snapshot.children) {
//                                        val permisoEmail = correoSnapshot.getValue(String::class.java)
//                                        if (permisoEmail == email) {
//                                            encontrado = true
//                                            // A que vista lo quiere dirigir
//                                            val intent = Intent(this, RegisterUser::class.java)
//                                            startActivity(intent)
//                                            finish()
//                                            break
//                                        }
//                                    }
//
//                                    // Si no se encontró en el grupo "1", buscar en el grupo "2"
//                                    if (!encontrado) {
//                                        val grupo2Snapshot = snapshot.child("2")
//                                        for (correoSnapshot in grupo2Snapshot.children) {
//                                            val permisoEmail = correoSnapshot.getValue(String::class.java)
//                                            if (permisoEmail == email) {
//                                                encontrado = true
//                                                val intent = Intent(this, PlayActivity::class.java)
//                                                startActivity(intent)
//                                                finish()
//                                                break
//                                            }
//                                        }
//                                    }
//
//                                    // Si el correo no se encuentra en ninguno de los grupos, muestra un mensaje
//                                    if (!encontrado) {
//                                        Toast.makeText(this, "Usuario no encontrado en Permisos", Toast.LENGTH_SHORT).show()
//                                    }
//                                }
//                                .addOnFailureListener {
//                                    Toast.makeText(this, "Error al obtener permisos", Toast.LENGTH_SHORT).show()
//                                }
//                        } else {
//                            // Mostrar un mensaje de error si el login falla
//                            Toast.makeText(baseContext, "Error en autenticación: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//            } else {
//                Toast.makeText(this, "Por favor ingresa el correo y la contraseña", Toast.LENGTH_SHORT).show()
//            }
//        }

        // Configuración de la interfaz de usuario utilizando Jetpack Compose
        setContent {
            ProyectoIntegradorTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
