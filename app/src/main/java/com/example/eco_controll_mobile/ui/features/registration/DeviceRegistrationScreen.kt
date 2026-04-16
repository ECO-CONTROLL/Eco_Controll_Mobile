package com.example.eco_controll_mobile.ui.features.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.DarkBackground
import com.example.eco_controll_mobile.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceRegistrationScreen(onBackClick: () -> Unit) {
    var deviceType by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }
    var brand by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(DarkBackground).padding(16.dp)) {
        Text("Cadastrar Equipamento", color = PrimaryGreen, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Adicione uma nova Cisterna ou Placa Solar", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = deviceType,
            onValueChange = { deviceType = it },
            label = { Text("Tipo (Ex: Cisterna, Painel Solar)") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = PrimaryGreen,
                unfocusedLabelColor = Color.Gray,
                cursorColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = deviceType,
            onValueChange = { deviceType = it },
            label = { Text("Tipo (Ex: Cisterna, Painel Solar)") },
            // NOVO PADRÃO DE CORES AQUI:
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = PrimaryGreen,
                unfocusedLabelColor = Color.Gray,
                cursorColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = capacity,
            onValueChange = { capacity = it },
            label = { Text("Capacidade (Ex: 1000L, 500W)") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = PrimaryGreen,
                unfocusedLabelColor = Color.Gray,
                cursorColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = brand,
            onValueChange = { brand = it },
            label = { Text("Marca / Modelo") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = PrimaryGreen,
                unfocusedLabelColor = Color.Gray,
                cursorColor = PrimaryGreen
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Salvar Equipamento", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}