package com.example.csti.data

import java.sql.Date

data class historial(
    val id_historial: Int,
    val id_solicitud: Int,
    val id_usuario_asignado: Int,
    val id_usuario_cedido: Int,
    val fecha_hora: Date,
)
