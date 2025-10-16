package com.example.gatiemergencias.Navigation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun botonGrande(
    onClick: () -> Unit,
    containerColor: Color = Color.Blue,   // color por defecto
    contentColor: Color = Color.White     // color del icono por defecto
) {
    LargeFloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Large floating action button")
    }
}