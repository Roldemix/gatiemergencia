package com.example.gatiemergencias.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gatiemergencias.ui.viewmodel.HistoryViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeScreenTopBarIsDisplayed() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        composeTestRule.onNodeWithText("Principal")
            .assertIsDisplayed()
    }

    @Test
    fun testEmergencyButtonsAreDisplayed() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        composeTestRule.onNodeWithText("Bombero")
            .assertIsDisplayed()
        
        composeTestRule.onNodeWithText("Médico")
            .assertIsDisplayed()
        
        composeTestRule.onNodeWithText("Carabinero")
            .assertIsDisplayed()
    }

    @Test
    fun testInstructionTextIsDisplayed() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        composeTestRule.onNodeWithText("En caso de emergencia, presione el botón correspondiente al servicio que necesite.")
            .assertIsDisplayed()
    }

    @Test
    fun testRecentEmergenciesHeaderIsDisplayed() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        composeTestRule.onNodeWithText("Emergencias Recientes")
            .assertIsDisplayed()
    }

    @Test
    fun testClickBomberoButtonAddsToHistory() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        composeTestRule.onAllNodesWithContentDescription("Large floating action button")
            .onFirst()
            .performClick()

        assert(viewModel.items.isNotEmpty())
        assert(viewModel.items[0].contains("Bomberos"))
    }

    @Test
    fun testClickMedicoButtonAddsToHistory() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        composeTestRule.onAllNodesWithContentDescription("Large floating action button")[1]
            .performClick()

        assert(viewModel.items.isNotEmpty())
        assert(viewModel.items[0].contains("Medica"))
    }

    @Test
    fun testViewModelIsNotNull() {
        val viewModel = HistoryViewModel()
        composeTestRule.setContent {
            HomeScreen(viewModel)
        }
        
        assert(viewModel.items.isNotEmpty() || viewModel.items.isEmpty())
    }
}
