package com.example.csti

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csti.crud.obtenerSolicitudes
import com.example.csti.data.solicitud
import kotlinx.coroutines.launch

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

enum class Estatus {
    PENDIENTE, RESUELTO, EN_PROCESO
}

class MainViewModel : ViewModel() {
    private val _reportes = mutableStateOf<List<solicitud>>(emptyList())
    val solicitudes: List<solicitud> get() = _reportes.value

    init {
        loadReportes()
    }

    private fun loadReportes() {
        viewModelScope.launch {
            // Cargar datos reales desde la base de datos con manejo de errores
            val result: Result<List<solicitud>> = try {
                val datos = obtenerSolicitudes()
                Result.Success(datos)
            } catch (e: Exception) {
                Result.Error(e)
            }
            when (result) {
                is Result.Success -> _reportes.value = result.data
                is Result.Error -> {
                    // Manejar el error (ej. mostrar un mensaje al usuario)
                }
            }
        }
    }
}
