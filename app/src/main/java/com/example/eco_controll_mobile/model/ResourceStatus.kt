package com.example.eco_controll_mobile.model

data class CisternStatus(
    val nivelPercentual: Float,
    val capacidadeTotalLitros: Int,
    val consumoDiarioLitros: Int,
    val autonomiaDias: Int
)

data class SolarStatus(
    val geracaoAtualKwh: Float,
    val geracaoMensalKwh: Float,
    val economiaGeradaReais: Float,
    val eficienciaPercentual: Int
)