package com.example.notas_system.data.repository

import com.example.notas_system.data.models.Usuario

object UserRepository {
    private val users = mutableListOf<Usuario>()

    init {
        //test user
        users.add(Usuario("root","toor"))
    }

    fun register(user: Usuario): Boolean {
        if (users.any { it.username == user.username }) return false
        users.add(user)
        return true
    }

    fun login(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }
}