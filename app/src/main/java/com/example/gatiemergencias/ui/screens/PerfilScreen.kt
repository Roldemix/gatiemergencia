package com.example.gatiemergencias.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.text.input.PasswordVisualTransformation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun perfilscreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Perfil") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val context = LocalContext.current
            val auth = FirebaseAuth.getInstance()
            val db = FirebaseFirestore.getInstance()

            val currentUid = auth.currentUser?.uid

            if (currentUid != null) {
                // Usuario autenticado: cargar perfil desde Firestore
                var name by remember { mutableStateOf<String?>(null) }
                var lastName by remember { mutableStateOf<String?>(null) }
                var edad by remember { mutableStateOf<String?>(null) }
                var descripcion by remember { mutableStateOf<String?>(null) }
                var emailProfile by remember { mutableStateOf<String?>(auth.currentUser?.email) }
                var loading by remember { mutableStateOf(true) }

                LaunchedEffect(currentUid) {
                    db.collection("users").document(currentUid).get()
                        .addOnSuccessListener { doc ->
                            if (doc != null && doc.exists()) {
                                // Intentar leer varios posibles nombres de campo
                                name = doc.getString("name") ?: doc.getString("nombre")
                                lastName = doc.getString("lastName") ?: doc.getString("Lastname")
                                edad = doc.getString("edad") ?: doc.get("edad")?.toString()
                                descripcion = doc.getString("descripcion") ?: doc.getString("description")
                                emailProfile = doc.getString("email") ?: auth.currentUser?.email
                            }
                            loading = false
                        }
                        .addOnFailureListener { e ->
                            loading = false
                            Toast.makeText(context, "Error al cargar perfil: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                }

                if (loading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Nombre: ${name ?: "--"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Apellido: ${lastName ?: "--"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Edad: ${edad ?: "--"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Email: ${emailProfile ?: "--"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Descripcion: ${descripcion ?: "--"}")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            auth.signOut()
                            Toast.makeText(context, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                            navController.navigate("home")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar sesión")
                    }
                }

            } else {
                // Usuario no autenticado: mostrar formulario de login
                var contrasena by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var signingIn by remember { mutableStateOf(false) }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    label = { Text("Contrasena") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),

                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (email.isBlank() || contrasena.isBlank()) {
                            Toast.makeText(context, "Por favor, complete email y contraseña", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        try {
                            signingIn = true
                            auth.signInWithEmailAndPassword(email, contrasena)
                                .addOnCompleteListener { task ->
                                    signingIn = false
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
                                        navController.navigate("home")
                                    } else {
                                        val errormessage = when (task.exception) {
                                            is FirebaseAuthInvalidCredentialsException -> "correo o contraseña incorrectos"
                                            is FirebaseAuthInvalidUserException -> "no existe una cuenta con este correo"
                                            is FirebaseAuthEmailException -> "el formato del correo no es valido"
                                            else -> "error al iniciar sesion. intentelo de nuevo"
                                        }
                                        Toast.makeText(context, errormessage, Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } catch (e: IllegalArgumentException) {
                            signingIn = false
                            Toast.makeText(context, "Entrada inválida: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            signingIn = false
                            Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !signingIn
                ) {
                    Text(if (signingIn) "Iniciando..." else "Iniciar sesion")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.navigate("sign in") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registro")
                }
            }
        }
    }
}