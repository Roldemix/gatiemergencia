package com.example.gatiemergencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gatiemergencias.Navigation.BottomBar
import com.example.gatiemergencias.Navigation.BottomNavItem
import com.example.gatiemergencias.Navigation.Routes
import com.example.gatiemergencias.ui.screens.HistoryScreen
import com.example.gatiemergencias.ui.screens.HomeScreen
import com.example.gatiemergencias.ui.screens.ProfileScreen
import com.example.gatiemergencias.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme { App() }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.History)

    Scaffold(
        bottomBar = { BottomBar(navController, bottomItems) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen()
            }
            composable(Routes.PROFILE) {
                ProfileScreen()
            }
            composable(Routes.HISTORY) {
                HistoryScreen()
            }
        }
    }
}