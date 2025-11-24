package com.example.gatiemergencias.ui.screens

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
import androidx.navigation.compose.composable
import com.example.gatiemergencias.Navigation.Routes
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun perfilscreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("iniciar Session/Registro") }) }
    ) { padding ->
        Column(

            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("profile")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesion")
            }

            Button(
                onClick = {
                    navController.navigate("sign in")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registro")
            }
        }
    }
}