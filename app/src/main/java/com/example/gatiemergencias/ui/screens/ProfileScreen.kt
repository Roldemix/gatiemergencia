package com.example.gatiemergencias.ui.screens


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
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
            val snackHostState = remember { SnackbarHostState() }

            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Contrasena") },
                modifier = Modifier.fillMaxWidth()
            )



            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (email.isBlank()) {
                        Toast.makeText(context, "por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    auth.signInWithEmailAndPassword(email, name)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
                                navController.navigate("home")
                            } else {
                                val errormessage = when (task.exception) {
                                   is FirebaseAuthInvalidCredentialsException -> "correo o contraseña incorrectos"
                                    is FirebaseAuthInvalidUserException -> "no existe una cuenta con este correo"
                                    is FirebaseAuthEmailException -> "el formato del correo no es valido"
                                    else -> "error al inicir sesion. intentelo de nuevo"
                                }
                                Toast.makeText(context, errormessage, Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesion")
            }
        }
    }
}
