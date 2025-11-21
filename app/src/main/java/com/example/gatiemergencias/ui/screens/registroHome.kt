package com.example.gatiemergencias.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registroHome(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Registro") }) }
    ){ padding ->
        Column(

            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val context = LocalContext.current
            val auth = FirebaseAuth.getInstance()
            val snackHostState = remember { SnackbarHostState() }
            val db = Firebase.firestore

            var contrasenia by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }
            var Lastname by remember { mutableStateOf("") }
            var edad by remember { mutableStateOf("") }
            var descripcion by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it },
                label = { Text("Contrasena") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = Lastname,
                onValueChange = { Lastname = it },
                label = { Text("Lastname") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("edad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("descripcion") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))


            Button(
                onClick = {
                    // Create a new user with a first and last name
                    auth.createUserWithEmailAndPassword(email, contrasenia)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val uid = auth.currentUser!!.uid
                                val profile = mapOf(
                                    "name" to name,
                                    "Lastname" to Lastname,
                                    "edad" to edad,
                                    "descripcion" to descripcion,
                                    "email" to email,
                                    "createdAt" to com.google.firebase.firestore.FieldValue.serverTimestamp()
                                )
                                Toast.makeText(context, "Cuenta creada con exito", Toast.LENGTH_LONG).show()
                                db.collection("users").document(uid).set(profile)
                                    .addOnSuccessListener {
                                        Log.d(TAG, "Perfil creado para uid=$uid")
                                        navController.navigate("home")
                                    }
                                    .addOnFailureListener { e -> Log.w(TAG, "Error al guardar perfil", e) }
                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                        }
                    navController.navigate("home")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("crear cuenta")
            }
        }
    }
}
