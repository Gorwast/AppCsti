package com.example.csti.data

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    testConnection()
}

fun testConnection() {
    try {
        // Registrar el driver manualmente
        Class.forName("com.mysql.cj.jdbc.Driver")
        val connection: Connection = Database.getConnection()
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT 1")
        if (resultSet.next()) {
            println("Conexión exitosa!")
        }
        connection.close()
    } catch (e: SQLException) {
        println("Error al conectar: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("Driver no encontrado: ${e.message}")
    }
}
