package com.example.csti.crud
/**
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

fun insertarHistorial(historial: historial) {
    val connection: Connection = Database.getConnection()
    val query = "INSERT INTO historial (id_historial, id_solicitud, id_usuario_asignado, id_usuario_cedido, fecha_hora) VALUES (?, ?, ?, ?, ?)"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, historial.id_historial)
    preparedStatement.setInt(2, historial.id_solicitud)
    preparedStatement.setInt(3, historial.id_usuario_asignado)
    preparedStatement.setInt(4, historial.id_usuario_cedido)
    preparedStatement.setDate(5, historial.fecha_hora)
    preparedStatement.executeUpdate()
    connection.close()
}

fun obtenerHistoriales(): List<historial> {
    val connection: Connection = Database.getConnection()
    val query = "SELECT * FROM historial"
    val statement: Statement = connection.createStatement()
    val resultSet: ResultSet = statement.executeQuery(query)
    val historiales = mutableListOf<historial>()
    while (resultSet.next()) {
        val historial = historial(
            resultSet.getInt("id_historial"),
            resultSet.getInt("id_solicitud"),
            resultSet.getInt("id_usuario_asignado"),
            resultSet.getInt("id_usuario_cedido"),
            resultSet.getDate("fecha_hora")
        )
        historiales.add(historial)
    }
    connection.close()
    return historiales
}

fun actualizarHistorial(historial: historial) {
    val connection: Connection = Database.getConnection()
    val query = "UPDATE historial SET id_solicitud = ?, id_usuario_asignado = ?, id_usuario_cedido = ?, fecha_hora = ? WHERE id_historial = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, historial.id_solicitud)
    preparedStatement.setInt(2, historial.id_usuario_asignado)
    preparedStatement.setInt(3, historial.id_usuario_cedido)
    preparedStatement.setDate(4, historial.fecha_hora)
    preparedStatement.setInt(5, historial.id_historial)
    preparedStatement.executeUpdate()
    connection.close()
}

fun borrarHistorial(id_historial: Int) {
    val connection: Connection = Database.getConnection()
    val query = "DELETE FROM historial WHERE id_historial = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, id_historial)
    preparedStatement.executeUpdate()
    connection.close()
}
*/