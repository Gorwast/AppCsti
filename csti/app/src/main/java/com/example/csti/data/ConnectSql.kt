package com.example.csti.data

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.math.log


class ConnectSql {
    private val ip="187.244.131.32:3306"
    private val db="gestion_solicitudes"
    private val username="adminuser"
    private val password="AdminUser2024!"

    fun dbConnect(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        val connString : String
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user0$username;password=$password"
            conn = DriverManager.getConnection(connString)
        }catch (ex: SQLException){

            Log.e("Error: ", ex.message!!)
        }catch (ex1: ClassNotFoundException){
            Log.e("Error: ", ex1.message!! )
        }catch (ex2: Exception){
            Log.e("Error: ",ex2.message!!)
        }
        return conn
    }

}