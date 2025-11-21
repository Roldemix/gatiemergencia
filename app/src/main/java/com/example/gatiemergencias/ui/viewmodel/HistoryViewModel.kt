package com.example.gatiemergencias.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryViewModel : ViewModel() {
    private val _items = mutableStateListOf<String>()
    val items: SnapshotStateList<String> get() = _items

    private val db = FirebaseFirestore.getInstance()
    private var listener: ListenerRegistration? = null

    init {
        // Escuchar la colección 'emergencies' en tiempo real, ordenada por createdAt descendente
        listener = db.collection("emergencies")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    // En caso de error, no actualizar la lista
                    return@addSnapshotListener
                }
                if (snapshots != null) {
                    _items.clear()
                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                    for (doc in snapshots.documents) {
                        val label = doc.getString("label") ?: doc.getString("type") ?: "Emergencia"
                        val ts = doc.getTimestamp("createdAt")
                        val timeStr = ts?.toDate()?.let { sdf.format(it) } ?: ""
                        val display = if (timeStr.isNotEmpty()) "$label — $timeStr" else label
                        _items.add(display)
                    }
                }
            }
    }

    /**
     * Crea una nueva emergencia en Firestore. La entrada será replicada a todos los dispositivos.
     */
    fun addItem(label: String? = null) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val data = hashMapOf<String, Any?>(
            "label" to (label ?: "Emergencia"),
            "createdAt" to FieldValue.serverTimestamp(),
            "uid" to (currentUser?.uid)
        )
        db.collection("emergencies").add(data)
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}
