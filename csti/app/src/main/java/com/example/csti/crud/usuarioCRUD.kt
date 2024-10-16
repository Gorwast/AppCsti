package com.example.csti.crud

import com.example.csti.data.ConnectSql
import com.example.csti.data.Database
import com.example.csti.data.usuario
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

//private var connectSql = ConnectSql()
fun insertarUsuario(usuario: usuario) {
    val connection: Connection = Database.getConnection()
    val query = "INSERT INTO usuario (id_usuario, nombre_usuario, nombre, correo, contrasena, foto, contacto, rol, estatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, usuario.id_usuario)
    preparedStatement.setString(2, usuario.nombre_usuario)
    preparedStatement.setString(3, usuario.nombre.toString())
    preparedStatement.setString(4, usuario.correo)
    preparedStatement.setString(5, usuario.contrasena)
    preparedStatement.setString(6, usuario.foto)
    preparedStatement.setString(7, usuario.contacto?.toString())
    preparedStatement.setString(8, usuario.rol)
    preparedStatement.setString(9, usuario.estatus)
    preparedStatement.executeUpdate()
    connection.close()
}

fun obtenerUsuarios(): List<usuario> {
    val connection: Connection = Database.getConnection()
    val query = "USE gestion_solicitudes;\n" +
            "SELECT * FROM usuario;"
    val statement: Statement = connection.createStatement()
    val resultSet: ResultSet = statement.executeQuery(query)
    val usuarios = mutableListOf<usuario>()
    try {
        while (resultSet.next()) {
            val usuario = usuario(
                resultSet.getInt("id_usuario"),
                resultSet.getString("nombre_usuario"),
                resultSet.getString("nombre")[0],
                resultSet.getString("correo"),
                resultSet.getString("contrasena"),
                resultSet.getString("foto"),
                resultSet.getString("contacto")?.get(0),
                resultSet.getString("rol"),
                resultSet.getString("estatus")
            )
            usuarios.add(usuario)
        }
    } finally {
        resultSet.close()
        statement.close()
        connection.close() // Close the connection
    }
    return usuarios
}

/*fun obtenerUsuarios(): List<usuario> {
    //val connection: PreparedStatement = connectSql.dbConnect().prepareStatement("SELECT * FROM usuario")!!
    //val query = "use gestion_solicitudes; SELECT * FROM usuario;"
    val connect:
    val statement: Statement = connection.createStatement()
    val resultSet: ResultSet = statement.executeQuery(query)
    val usuarios = mutableListOf<usuario>()
    while (resultSet.next()) {
        val usuario = usuario(
            resultSet.getInt("id_usuario"),
            resultSet.getString("nombre_usuario"),
            resultSet.getString("nombre")[0],
            resultSet.getString("correo"),
            resultSet.getString("contrasena"),
            resultSet.getString("foto"),
            resultSet.getString("contacto")?.get(0),
            resultSet.getString("rol"),
            resultSet.getString("estatus")
        )
        usuarios.add(usuario)
    }
    connection.close()
    return usuarios
}*/

fun actualizarUsuario(usuario: usuario) {
    val connection: Connection = Database.getConnection()
    val query = "UPDATE usuario SET nombre_usuario = ?, nombre = ?, correo = ?, contrasena = ?, foto = ?, contacto = ?, rol = ?, estatus = ? WHERE id_usuario = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setString(1, usuario.nombre_usuario)
    preparedStatement.setString(2, usuario.nombre.toString())
    preparedStatement.setString(3, usuario.correo)
    preparedStatement.setString(4, usuario.contrasena)
    preparedStatement.setString(5, usuario.foto)
    preparedStatement.setString(6, usuario.contacto?.toString())
    preparedStatement.setString(7, usuario.rol)
    preparedStatement.setString(8, usuario.estatus)
    preparedStatement.setInt(9, usuario.id_usuario)
    preparedStatement.executeUpdate()
    connection.close()
}

fun borrarUsuario(id_usuario: Int) {
    val connection: Connection = Database.getConnection()
    val query = "DELETE FROM usuario WHERE id_usuario = ?"
    val preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, id_usuario)
    preparedStatement.executeUpdate()
    connection.close()
}
