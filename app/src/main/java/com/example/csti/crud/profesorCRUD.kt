package com.example.csti

import com.google.firebase.firestore.FirebaseFirestore

class ProfesorCRUD(private val db: FirebaseFirestore) {

    fun createProfesor() {
        val profesor = hashMapOf<String, Any>(
            "id_profesor" to 3145,
            "nombre_profesor" to "Ivan Chavez"
        )

        db.collection("profesor")
            .add(profesor)
            .addOnSuccessListener { documentReference ->
                // Documento añadido con éxito
            }
            .addOnFailureListener { e ->
                // Error al añadir el documento
            }
    }

    fun readProfesores() {
        db.collection("profesor")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Procesa cada documento
                }
            }
            .addOnFailureListener { e ->
                // Error al obtener los documentos
            }
    }

    fun updateProfesor(documentId: String) {
        val profesorUpdates = hashMapOf<String, Any>(
            "nombre_profesor" to "Ivan Chavez Actualizado"
        )

        db.collection("profesor").document(documentId)
            .update(profesorUpdates)
            .addOnSuccessListener {
                // Documento actualizado con éxito
            }
            .addOnFailureListener { e ->
                // Error al actualizar el documento
            }
    }

    fun deleteProfesor(documentId: String) {
        db.collection("profesor").document(documentId)
            .delete()
            .addOnSuccessListener {
                // Documento eliminado con éxito
            }
            .addOnFailureListener { e ->
                // Error al eliminar el documento
            }
    }
}
