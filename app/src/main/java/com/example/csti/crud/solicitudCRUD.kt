package com.example.csti

import com.google.firebase.firestore.FirebaseFirestore

class SolicitudCRUD(private val db: FirebaseFirestore) {

    fun createSolicitud() {
        val solicitud = hashMapOf<String, Any>(
            "aula" to "5G-201",
            "descripcion_problema" to "No enciende el proyector",
            "estatus" to "sin",
            "fecha_hora_envio" to "21 de octubre de 2024, 9:11:30 a.m. UTC-7",
            "id_solicitud" to "S1"
        )

        db.collection("Solicitud")
            .add(solicitud)
            .addOnSuccessListener { documentReference ->
                // Documento añadido con éxito
            }
            .addOnFailureListener { e ->
                // Error al añadir el documento
            }
    }

    fun readSolicitudes() {
        db.collection("Solicitud")
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

    fun updateSolicitud(documentId: String) {
        val solicitudUpdates = hashMapOf<String, Any>(
            "estatus" to "actualizado"
        )

        db.collection("Solicitud").document(documentId)
            .update(solicitudUpdates)
            .addOnSuccessListener {
                // Documento actualizado con éxito
            }
            .addOnFailureListener { e ->
                // Error al actualizar el documento
            }
    }

    fun deleteSolicitud(documentId: String) {
        db.collection("Solicitud").document(documentId)
            .delete()
            .addOnSuccessListener {
                // Documento eliminado con éxito
            }
            .addOnFailureListener { e ->
                // Error al eliminar el documento
            }
    }
}
