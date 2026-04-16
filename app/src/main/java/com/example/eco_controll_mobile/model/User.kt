package com.example.eco_controll_mobile.model

data class User(
    val id: String,
    val nome: String,
    val email: String,
    val telefone: String,
    val fotoUrl: String? = null
)