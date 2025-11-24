package com.example.gatiemergencias.ui.viewmodel

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class HistoryViewModelTest {
    private lateinit var viewModel: HistoryViewModel

    @Before
    fun setUp() {
        viewModel = HistoryViewModel()
    }

    @Test
    fun testInitialListIsEmpty() {
        assertTrue(viewModel.items.isEmpty())
    }

    @Test
    fun testAddItemWithLabel() {
        viewModel.addItem("Bomberos")
        
        assertEquals(1, viewModel.items.size)
        assertEquals("Bomberos", viewModel.items[0])
    }

    @Test
    fun testAddItemWithoutLabel() {
        viewModel.addItem()
        
        assertEquals(1, viewModel.items.size)
        assertEquals("Caja 1", viewModel.items[0])
    }

    @Test
    fun testAddMultipleItems() {
        viewModel.addItem("Bomberos")
        viewModel.addItem("Medica")
        viewModel.addItem("Carabineros")
        
        assertEquals(3, viewModel.items.size)
        assertEquals("Carabineros", viewModel.items[0])
        assertEquals("Medica", viewModel.items[1])
        assertEquals("Bomberos", viewModel.items[2])
    }

    @Test
    fun testAddItemsInCorrectOrder() {
        viewModel.addItem("Primer Item")
        assertEquals("Primer Item", viewModel.items[0])
        
        viewModel.addItem("Segundo Item")
        assertEquals("Segundo Item", viewModel.items[0])
        assertEquals("Primer Item", viewModel.items[1])
    }

    @Test
    fun testDefaultLabelWithMultipleItems() {
        viewModel.addItem()
        viewModel.addItem()
        viewModel.addItem()
        
        assertEquals(3, viewModel.items.size)
        assertEquals("Caja 3", viewModel.items[0])
        assertEquals("Caja 2", viewModel.items[1])
        assertEquals("Caja 1", viewModel.items[2])
    }
}
