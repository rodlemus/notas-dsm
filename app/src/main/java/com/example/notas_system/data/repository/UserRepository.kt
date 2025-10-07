package com.example.notas_system.data.repository

import android.content.Context
import com.example.notas_system.data.models.Usuario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object UserRepository {
    private const val FILE_NAME = "users.json"
    private val gson = Gson()

    private fun getFile(context: Context): File {
        return File(context.filesDir, FILE_NAME)
    }

    fun cargarUsuarios(context: Context): MutableList<Usuario> {
        val file = getFile(context)
        if (!file.exists()) return mutableListOf()
        val json = file.reader()
        val type = object : TypeToken<MutableList<Usuario>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    fun guardarUsuarios(context: Context, users: List<Usuario>) {
        val file = getFile(context)
        file.writeText((gson.toJson(users)))
    }
}