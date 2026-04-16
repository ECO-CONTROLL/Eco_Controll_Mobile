package com.example.eco_controll_mobile.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val EcoControllColorScheme = darkColorScheme(
    background = DarkBackground,
    surface = CardBackground,
    primary = PrimaryGreen,
    onPrimary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun EcoControllTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EcoControllColorScheme,
        // Você pode adicionar typography e shapes personalizados aqui depois
        content = content
    )
}