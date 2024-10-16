package com.example.csti

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csti.crud.obtenerUsuarios
import com.example.csti.data.usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.SQLException

sealed class LoginResult {
    object Idle : LoginResult()
    data class Success(val user: usuario) : LoginResult()
    data class Error(val message: String) : LoginResult()
}

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginResult: StateFlow<LoginResult> get() = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val usuarios = obtenerUsuarios()
                val user = usuarios.find { it.nombre_usuario == username && it.contrasena == password }
                if (user != null) {
                    _loginResult.value = LoginResult.Success(user)
                } else {
                    _loginResult.value = LoginResult.Error("Nombre de usuario o contraseña incorrectos")
                }
            } catch (e: SQLException) {
                _loginResult.value = LoginResult.Error("Error de conexión SQL: ${e.message}")
            } catch (e: Exception) {
                _loginResult.value = LoginResult.Error("Error inesperado: ${e.message}")
            }
        }
    }
}
