package com.example.csti.data

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Database {
    fun getConnection(): Connection {
        val url = "jdbc:mysql://187.244.131.32:3306/gestion_solicitudes" // Reemplaza con la URL de tu base de datos
        val user = "adminuser" // Reemplaza con tu usuario
        val password = "AdminUser2024!" // Reemplaza con tu contraseña
        return DriverManager.getConnection(url, user, password)
    }
}
