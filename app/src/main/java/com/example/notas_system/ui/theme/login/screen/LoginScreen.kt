package com.example.notas_system.ui.theme.login.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
fun LoginScreen(viewModel: AuthViewModel, context: Context, onLoginSuccess: () -> Unit, onNavigateToRegister: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.correo,
            onValueChange = { viewModel.correo = it },
            label = { Text("Correo") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.login(context)
            if (viewModel.isLoggedIn) onLoginSuccess()
        }) {
            Text("Iniciar Sesión")
        }

        if (viewModel.loginErrorMessage.isNotEmpty()) {
            Text(viewModel.loginErrorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Texto para ir al registro
        Text(
            text = "¿Aún no tienes una cuenta? Regístrate",
            color = Color.Blue,
            modifier = Modifier.clickable { onNavigateToRegister() }
        )

    }
}
