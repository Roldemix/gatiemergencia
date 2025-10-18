package com.example.gatiemergencias.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gatiemergencias.Navigation.botonGrande
import com.example.gatiemergencias.ui.viewmodel.HistoryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(historyViewModel: HistoryViewModel? = null) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Principal") }) }
    ) { padding ->
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
              )
        {
            botonGrande(
                onClick = { /* acción aquí */ },
                containerColor = Color.Red,
                contentColor = Color.White
            )
            botonGrande(
                onClick = { /* acción aquí */ },
                containerColor = Color.Yellow,
                contentColor = Color.White
            )
            botonGrande(
                onClick = { /* acción aquí */ },
                containerColor = Color.Green,
                contentColor = Color.White
            )

        }
    }
}