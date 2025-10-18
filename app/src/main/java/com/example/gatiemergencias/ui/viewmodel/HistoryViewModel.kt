package com.example.gatiemergencias.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

/**
 * Simple ViewModel that holds a list of history items as a Compose-friendly state list.
 */
class HistoryViewModel : ViewModel() {
    private val _items = mutableStateListOf<String>()
    val items: SnapshotStateList<String> get() = _items

    fun addItem(label: String? = null) {
        val value = label ?: "Caja ${_items.size + 1}"
        _items.add(value)
    }
}
