package com.example.notas_system.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notas_system.data.models.Actividad

class NotasViewModel : ViewModel() {
    var notas = mutableStateListOf<Actividad>()
    var promedio by mutableStateOf(0f)
    var aprobado by mutableStateOf(false)

    fun agregarNota(actividad: String, calificacion: Float) {
        notas.add(Actividad(actividad, calificacion))
        calcularPromedio()
    }

    private fun calcularPromedio() {
        if (notas.isNotEmpty()) {
            promedio = notas.map { it.calificacion }.average().toFloat()
            aprobado = promedio >= 6.0f
        } else {
            promedio = 0f
            aprobado = false
        }
    }
}