package com.example.notas_system.ui.theme.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notas_system.data.models.Usuario
import com.example.notas_system.data.repository.UserRepository

class LoginViewModel: ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoggedIn by mutableStateOf(false)
    var loginError by mutableStateOf("")

    fun login() {
        if (UserRepository.login(username, password)) {
            isLoggedIn = true
            loginError = ""
        } else {
            loginError = "Usuario o contraseña incorrectos"
        }
    }

    fun register() {
        if (UserRepository.register(Usuario(username, password))) {
            isLoggedIn = true
        } else {
            loginError = "El usuario ya existe"
        }
    }

}