package com.example.csti.data

data class solicitud(
    val id_solicitud: Int,
    val id_usuario_asignado: Int,
    val fecha_hora_envio: String,
    val id_profesor: Int,
    val aula: String,
    val estatus: String,
    val descripcion_problema: String,
    val tecnico_asignado: String?,
)