package com.example.csti

import com.google.firebase.firestore.FirebaseFirestore

class UsuarioCRUD(private val db: FirebaseFirestore) {

    fun createUsuario() {
        val usuario = hashMapOf<String, Any>(
            "contacto" to "6621115544",
            "contrasena" to "uwu99",
            "correo" to "mirianateran.dev@gmail.com",
            "estatus" to "activo",
            "id_usuario" to "U1",
            "nombre" to "miriana",
            "nombre_usuario" to "mirianateran",
            "rol" to "admin"
        )

        db.collection("Usuario")
            .add(usuario)
            .addOnSuccessListener { documentReference ->
                // Documento añadido con éxito
            }
            .addOnFailureListener { e ->
                // Error al añadir el documento
            }
    }

    fun readUsuarios() {
        db.collection("Usuario")
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

    fun updateUsuario(documentId: String) {
        val usuarioUpdates = hashMapOf<String, Any>(
            "estatus" to "actualizado"
        )

        db.collection("Usuario").document(documentId)
            .update(usuarioUpdates)
            .addOnSuccessListener {
                // Documento actualizado con éxito
            }
            .addOnFailureListener { e ->
                // Error al actualizar el documento
            }
    }

    fun deleteUsuario(documentId: String) {
        db.collection("Usuario").document(documentId)
            .delete()
            .addOnSuccessListener {
                // Documento eliminado con éxito
            }
            .addOnFailureListener { e ->
                // Error al eliminar el documento
            }
    }
}
