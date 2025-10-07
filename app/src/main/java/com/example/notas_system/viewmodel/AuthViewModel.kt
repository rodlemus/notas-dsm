package com.example.notas_system.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notas_system.data.models.Usuario
import com.example.notas_system.data.repository.UserRepository

class AuthViewModel: ViewModel() {
    var correo by mutableStateOf("")
    var password by mutableStateOf("")
    var nombres by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var confirmarPassword by mutableStateOf("")

    var loginErrorMessage by mutableStateOf("")
    var registerErrorMessage by mutableStateOf("")
    var isLoggedIn by mutableStateOf(false)

    var currentUser by mutableStateOf<Usuario?>(null)

    private var users = mutableListOf<Usuario>()

    fun cargarUsuarios(context: Context) {
        users = UserRepository.cargarUsuarios(context)
    }

    // Metodo para validar que el input de correo solo acepte formato de Email
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex)
    }

    fun login(context: Context) {

        if (correo.isEmpty() || password.isEmpty()) {
            loginErrorMessage = "Ambos campos deben llenarse"
            isLoggedIn = false
            return
        }

        if (!isValidEmail(correo)) {
            loginErrorMessage = "El correo no es válido"
            isLoggedIn = false
            return
        }

        users = UserRepository.cargarUsuarios(context)
        val user = users.find { it.correo == correo && it.password == password }
        if (user != null) {
            isLoggedIn = true
            currentUser = user
            println("Usuario logueado: ${currentUser?.nombres} ${currentUser?.apellidos}")
            loginErrorMessage = ""
        } else {
            isLoggedIn = false
            currentUser = null
            loginErrorMessage = "Correo o contraseña incorrectos"
        }
    }

    fun register(context: Context) {
        users = UserRepository.cargarUsuarios(context)

        if (!isValidEmail(correo)) {
            registerErrorMessage = "El correo ingresado no es válido"
            return
        }

        if (nombres.isBlank() || apellidos.isBlank() || correo.isBlank() || password.isBlank() || confirmarPassword.isBlank()) {
            registerErrorMessage = "Todos los campos son obligatorios"
            return
        }

        if (password != confirmarPassword) {
            registerErrorMessage = "Las contraseñas ingresadas no coinciden"
            return
        }

        val nuevoUsuario = Usuario(nombres, apellidos, correo, password)
        users.add(nuevoUsuario)
        UserRepository.guardarUsuarios(context, users)

        registerErrorMessage = ""
        isLoggedIn = true
        currentUser = nuevoUsuario
    }

    fun logout() {
        // 🔹 Resetea los campos
        correo = ""
        password = ""
        currentUser = null
        isLoggedIn = false
    }
}