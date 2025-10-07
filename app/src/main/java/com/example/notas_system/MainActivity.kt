package com.example.notas_system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notas_system.ui.navigation.AppNavigation
import com.example.notas_system.ui.theme.login.screen.LoginScreen
import com.example.notas_system.ui.theme.notas.screen.NotasScreen
import com.example.notas_system.ui.theme.signup.screen.SignupScreen
import com.example.notas_system.viewmodel.AuthViewModel
import com.example.notas_system.viewmodel.NotasViewModel

class MainActivity : ComponentActivity() {
    private val authViewModel = AuthViewModel()
    private val notasViewModel = NotasViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = AuthViewModel()
            viewModel.cargarUsuarios(this)

            // Aqui llamamos al navhost central
            AppNavigation(viewModel, this)
        }
    }
}