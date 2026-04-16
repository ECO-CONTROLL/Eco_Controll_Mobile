package com.example.eco_controll_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.eco_controll_mobile.ui.theme.EcoControllTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcoControllTheme {
                // Chama a estrutura principal do aplicativo
                EcoControllApp()
            }
        }
    }
}