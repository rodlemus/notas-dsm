package com.example.notas_system.ui.theme.notas.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.notas_system.viewmodel.NotasViewModel

@Composable
fun NotasScreen(viewModel: NotasViewModel) {
    var actividadInput by remember { mutableStateOf("") }
    var notaInput by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Agregamos campo de nombre de la actividad
        OutlinedTextField(
            value = actividadInput,
            onValueChange = { actividadInput = it },
            label = { Text("Nombre de la actividad") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Agregamos campo de nota
        OutlinedTextField(
            value = notaInput,
            onValueChange = { notaInput = it },
            label = { Text("Nota") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boton paraa agregar
        Button(onClick = {
            val nota = notaInput.toFloatOrNull()
            if(nota != null && actividadInput.isNotBlank()) {
                if(nota in 0f..10f) {
                    viewModel.agregarNota(actividadInput, nota)
                    notaInput = ""
                    mensajeError = ""
                } else {
                    mensajeError = "La nota debe estar entre 0.0 y 10.0"
                }
            } else {
                mensajeError = "Ingrese un nombre de actividad y nota validos"
            }
        }) {
            Text("Agregar Nota")
        }

        // Mostrar error si existe
        if (mensajeError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(mensajeError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Mostrar lista de notas
        Text("Notas ingresadas:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            viewModel.notas.forEach { nota ->
                Text("• ${nota.nombreActividad}: ${nota.calificacion}")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Promedio: ${"%.2f".format(viewModel.promedio)}")
        Text(
            text = if (viewModel.aprobado) "APROBADO" else "REPROBADO",
            color = if (viewModel.aprobado) Color.Green else Color.Red,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
