package com.example.csti.data

data class usuario(
    val id_usuario: Int,
    val nombre_usuario: String,
    val nombre: Char,
    val correo: String,
    val contrasena: String,
    val foto: String,
    val contacto: Char?,
    val rol: String, // Puede ser "Tecnico" o "Administrador"
    val estatus: String,
)