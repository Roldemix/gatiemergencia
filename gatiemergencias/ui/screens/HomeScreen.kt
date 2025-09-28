package com.example.gatiemergencias.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( ) {


    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("home") })

    }
}

