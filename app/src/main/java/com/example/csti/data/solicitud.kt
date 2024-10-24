package com.example.csti.data

import com.google.firebase.firestore.DocumentId

data class solicitud(
    @DocumentId
    val id: String? = null,
    val aula: String? = null,
    val descripcion_problema: String? = null,
    val estatus: String? = null,
    val fecha_hora_envio: Any? = null,
    val id_profesor: String? = null,
    val id_solicitud: String? = null
)