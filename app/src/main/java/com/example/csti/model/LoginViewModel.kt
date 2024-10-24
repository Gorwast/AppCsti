package com.example.csti.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csti.data.usuario
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginResult {
    object Idle : LoginResult()
    data class Success(val user: usuario) : LoginResult()
    data class Error(val message: String) : LoginResult()
}

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginResult: StateFlow<LoginResult> get() = _loginResult

    private val db = FirebaseFirestore.getInstance()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val query = db.collection("Usuario")
                    .whereEqualTo("nombre_usuario", username)
                    .whereEqualTo("contrasena", password)

                query.get()
                    .addOnSuccessListener { documents ->
                        if (!documents.isEmpty) {
                            for (document in documents) {
                                val user = document.toObject(usuario::class.java)
                                _loginResult.value = LoginResult.Success(user)
                                return@addOnSuccessListener
                            }
                        } else {
                            _loginResult.value = LoginResult.Error("Nombre de usuario o contraseña incorrectos")
                        }
                    }
                    .addOnFailureListener { exception ->
                        _loginResult.value = LoginResult.Error("Error de conexión Firebase: ${exception.message}")
                    }
            } catch (e: Exception) {
                _loginResult.value = LoginResult.Error("Error inesperado: ${e.message}")
            }
        }
    }
}
