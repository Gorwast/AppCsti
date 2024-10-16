package com.example.csti.crud

import com.example.csti.data.Database
import com.example.csti.data.profesor
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

fun insertarProfesor(profesor: profesor) {
    val connection: Connection = Database.getConnection()
    val query = "INSERT INTO profesor (id_profesor, nombre_profesor) VALUES (?, ?)"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, profesor.id_profesor)
    preparedStatement.setString(2, profesor.nombre_profesor)
    preparedStatement.executeUpdate()
    connection.close()
}

fun obtenerProfesores(): List<profesor> {
    val connection: Connection = Database.getConnection()
    val query = "SELECT * FROM profesor"
    val statement: Statement = connection.createStatement()
    val resultSet: ResultSet = statement.executeQuery(query)
    val profesores = mutableListOf<profesor>()
    while (resultSet.next()) {
        val profesor = profesor(
            resultSet.getInt("id_profesor"),
            resultSet.getString("nombre_profesor")
        )
        profesores.add(profesor)
    }
    connection.close()
    return profesores
}

fun actualizarProfesor(profesor: profesor) {
    val connection: Connection = Database.getConnection()
    val query = "UPDATE profesor SET nombre_profesor = ? WHERE id_profesor = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setString(1, profesor.nombre_profesor)
    preparedStatement.setInt(2, profesor.id_profesor)
    preparedStatement.executeUpdate()
    connection.close()
}

fun borrarProfesor(id_profesor: Int) {
    val connection: Connection = Database.getConnection()
    val query = "DELETE FROM profesor WHERE id_profesor = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, id_profesor)
    preparedStatement.executeUpdate()
    connection.close()
}
