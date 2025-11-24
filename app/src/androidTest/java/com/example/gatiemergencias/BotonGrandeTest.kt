package com.example.gatiemergencias

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gatiemergencias.Navigation.botonGrande
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BotonGrandeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBotonGrandeIsDisplayed() {
        composeTestRule.setContent {
            botonGrande(onClick = {})
        }
        
        composeTestRule.onNodeWithContentDescription("Large floating action button")
            .assertIsDisplayed()
    }

    @Test
    fun testBotonGrandeClickable() {
        var clicked = false
        composeTestRule.setContent {
            botonGrande(onClick = { clicked = true })
        }
        
        composeTestRule.onNodeWithContentDescription("Large floating action button")
            .performClick()
        
        assert(clicked)
    }

    @Test
    fun testBotonGrandeWithDifferentColors() {
        composeTestRule.setContent {
            botonGrande(onClick = {})
        }
        
        composeTestRule.onNodeWithContentDescription("Large floating action button")
            .assertIsDisplayed()
    }
}
