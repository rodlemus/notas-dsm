package com.example.notas_system.ui.theme.signup.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.notas_system.data.models.Usuario
import com.example.notas_system.data.repository.UserRepository
import com.example.notas_system.viewmodel.AuthViewModel

@Composable
fun SignupScreen(viewModel: AuthViewModel, context: Context, onRegisterSuccess: () -> Unit, onNavigateToLogin: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.nombres,
            onValueChange = { viewModel.nombres = it },
            label = { Text("Nombres") }
        )
        OutlinedTextField(
            value = viewModel.apellidos,
            onValueChange = { viewModel.apellidos = it },
            label = { Text("Apellidos") }
        )
        OutlinedTextField(
            value = viewModel.correo,
            onValueChange = { viewModel.correo = it },
            label = { Text("Correo") }
        )
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = viewModel.confirmarPassword,
            onValueChange = { viewModel.confirmarPassword = it },
            label = { Text("Confirmar Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.register(context)
            if (viewModel.isLoggedIn) onRegisterSuccess()
        }) {
            Text("Registrarse")
        }

        if (viewModel.registerErrorMessage.isNotEmpty()) {
            Text(viewModel.registerErrorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Texto para ir al login
        Text(
            text = "¿Ya tienes una cuenta? Inicia Sesión",
            color = Color.Blue,
            modifier = Modifier.clickable { onNavigateToLogin() }
        )
    }
}