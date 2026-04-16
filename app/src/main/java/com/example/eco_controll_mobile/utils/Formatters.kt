package com.example.eco_controll_mobile.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Formatters {
    // Formata um float para a moeda local (Ex: 287.5f -> R$ 287,50)
    fun formatCurrency(value: Float): String {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(value)
    }

    // Formata um timestamp para data (Ex: Date() -> 20/11/2025)
    fun formatDate(date: Date): String {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        return format.format(date)
    }

    // Formata litros de forma legível (Ex: 10000 -> 10.000 L)
    fun formatLiters(liters: Int): String {
        val format = NumberFormat.getNumberInstance(Locale("pt", "BR"))
        return "${format.format(liters)} L"
    }
}