package com.example.notas_system.ui.theme.login.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.notas_system.viewmodel.AuthViewModel


@Composable
fun LoginScreen(viewModel: AuthViewModel, onLoginSuccess: () -> Unit, onGoToSignUp: () -> Unit){
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        TextField(
            value = viewModel.username,
            onValueChange = { viewModel.username = it },
            label = { Text("Usuario") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        // 🔹 Contenedor tipo "div" para los botones
        Column(
            modifier = Modifier.padding(top = 24.dp), // padding-top
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.login()
                if (viewModel.isLoggedIn) onLoginSuccess()
            }) {
                Text("Iniciar Sesión")
            }

            Spacer(modifier = Modifier.height(8.dp)) // espacio entre botones

            Button(onClick = onGoToSignUp) {
                Text("Registrarse")
            }
        }
        if (viewModel.loginError.isNotEmpty()) {
            Text(viewModel.loginError, color = Color.Red)
        }
    }
}