package com.example.eco_controll_mobile.ui.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.DarkBackground
import com.example.eco_controll_mobile.ui.theme.PrimaryGreen
import kotlinx.coroutines.delay
import androidx.compose.ui.res.painterResource
import com.example.eco_controll_mobile.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    // O LaunchedEffect inicia junto com a tela. O "delay" não trava o app.
    LaunchedEffect(key1 = true) {
        delay(2000L) // Aguarda 2000 milissegundos (2 segundos)
        onTimeout()  // Aciona a função de navegação após o tempo
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // NOTA: Quando você importar sua imagem real para o Android Studio (na pasta res/drawable),
        // você pode usar o componente Image() aqui. Por enquanto, recriei o visual com texto.

        Image(
           painter = painterResource(id = R.drawable.logo),
           contentDescription = "Logo",
           modifier = Modifier.size(150.dp)
       )


        Text(
            text = "EcoControll",
            color = PrimaryGreen,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Gestão inteligente da sua casa sustentável",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}