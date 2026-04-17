package com.example.eco_controll_mobile.ui.features.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarRegistrationScreen(onBackClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }
    var panelCount by remember { mutableStateOf("") }
    var occupiedArea by remember { mutableStateOf("") }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
                title = { Text("Novo Sistema Solar", color = SolarOrange, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { Icon(Icons.Default.ArrowBack, null, tint = TextPrimary) }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(value = name, onValueChange = { name = it }, label = "Nome do Sistema (Ex: Telhado Principal)")
            CustomTextField(value = model, onValueChange = { model = it }, label = "Modelo / Marca da Placa")
            CustomTextField(value = power, onValueChange = { power = it }, label = "Potência Total do Sistema (kWp)")
            CustomTextField(value = panelCount, onValueChange = { panelCount = it }, label = "Quantidade de Placas Solares")
            CustomTextField(value = occupiedArea, onValueChange = { occupiedArea = it }, label = "Área Ocupada Pelas Placas (m²)")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth().height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SolarOrange),
                shape = RoundedCornerShape(16.dp)
            ) { Text("Salvar Sistema Solar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) }
        }
    }
}