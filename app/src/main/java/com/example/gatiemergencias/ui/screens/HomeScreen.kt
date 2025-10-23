package com.example.gatiemergencias.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gatiemergencias.Navigation.botonGrande
import com.example.gatiemergencias.ui.viewmodel.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(historyViewModel: HistoryViewModel? = null) {
    val vm = historyViewModel ?: viewModel<HistoryViewModel>()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Principal") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        botonGrande(
                            onClick = { historyViewModel?.addItem("Bomberos") },
                            containerColor = Color(0xFFCE0D0D),
                            contentColor = Color.White
                        )
                        Text(
                            text = "Bombero",
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        botonGrande(
                            onClick = { historyViewModel?.addItem("Medica") },
                            containerColor = Color(0xFF2C16CB),

                        )
                        Text(
                            text = "Médico",
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        botonGrande(
                            onClick = { historyViewModel?.addItem("Carabineros") },
                            containerColor = Color(0xFF007327),
                            contentColor = Color.White
                        )
                        Text(
                            text = "Carabinero",
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Text(
                    text = "En caso de emergencia, presione el botón correspondiente al servicio que necesite.",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Recent emergencies section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Emergencias Recientes",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(vm.items.take(5)) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Box(modifier = Modifier.padding(12.dp)) {
                                Text(text = item)
                            }
                        }
                    }
                }
            }
        }
    }
}