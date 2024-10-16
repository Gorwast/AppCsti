package com.example.csti.crud

import com.example.csti.data.Database
import com.example.csti.data.solicitud
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

fun insertarSolicitud(solicitud: solicitud) {
    val connection: Connection = Database.getConnection()
    val query = "INSERT INTO solicitud (id_solicitud, id_usuario_asignado, fecha_hora_envio, id_profesor, aula, estatus, descripcion_problema, tecnico_asignado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, solicitud.id_solicitud)
    preparedStatement.setInt(2, solicitud.id_usuario_asignado)
    preparedStatement.setString(3, solicitud.fecha_hora_envio)
    preparedStatement.setInt(4, solicitud.id_profesor)
    preparedStatement.setString(5, solicitud.aula)
    preparedStatement.setString(6, solicitud.estatus)
    preparedStatement.setString(7, solicitud.descripcion_problema)
    preparedStatement.setString(8, solicitud.tecnico_asignado)
    preparedStatement.executeUpdate()
    connection.close()
}

fun obtenerSolicitudes(): List<solicitud> {
    val connection: Connection = Database.getConnection()
    val query = "SELECT * FROM solicitud"
    val statement: Statement = connection.createStatement()
    val resultSet: ResultSet = statement.executeQuery(query)
    val solicitudes = mutableListOf<solicitud>()
    while (resultSet.next()) {
        val solicitud = solicitud(
            resultSet.getInt("id_solicitud"),
            resultSet.getInt("id_usuario_asignado"),
            resultSet.getString("fecha_hora_envio"),
            resultSet.getInt("id_profesor"),
            resultSet.getString("aula"),
            resultSet.getString("estatus"),
            resultSet.getString("descripcion_problema"),
            resultSet.getString("tecnico_asignado")
        )
        solicitudes.add(solicitud)
    }
    connection.close()
    return solicitudes
}

fun actualizarSolicitud(solicitud: solicitud) {
    val connection: Connection = Database.getConnection()
    val query = "UPDATE solicitud SET id_usuario_asignado = ?, fecha_hora_envio = ?, id_profesor = ?, aula = ?, estatus = ?, descripcion_problema = ?, tecnico_asignado = ? WHERE id_solicitud = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, solicitud.id_usuario_asignado)
    preparedStatement.setString(2, solicitud.fecha_hora_envio)
    preparedStatement.setInt(3, solicitud.id_profesor)
    preparedStatement.setString(4, solicitud.aula)
    preparedStatement.setString(5, solicitud.estatus)
    preparedStatement.setString(6, solicitud.descripcion_problema)
    preparedStatement.setString(7, solicitud.tecnico_asignado)
    preparedStatement.setInt(8, solicitud.id_solicitud)
    preparedStatement.executeUpdate()
    connection.close()
}

fun borrarSolicitud(id_solicitud: Int) {
    val connection: Connection = Database.getConnection()
    val query = "DELETE FROM solicitud WHERE id_solicitud = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, id_solicitud)
    preparedStatement.executeUpdate()
    connection.close()
}
