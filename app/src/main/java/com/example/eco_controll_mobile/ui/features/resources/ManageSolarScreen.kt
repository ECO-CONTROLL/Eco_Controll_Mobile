package com.example.eco_controll_mobile.ui.features.resources

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material.icons.rounded.TrackChanges
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.rounded.WifiTethering
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageSolarScreen(onNavigateBack: () -> Unit) {

    // Estados interativos
    var activeMonitoring by remember { mutableStateOf(true) }
    var lowGenerationAlert by remember { mutableStateOf(true) }
    var ecoMode by remember { mutableStateOf(false) }
    var dailyGoal by remember { mutableStateOf(15f) }
    var connectivity by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
                title = { Text("Lincoln", color = PrimaryGreen, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = TextPrimary) }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Cabeçalho da Tela
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 16.dp)) {
                Box(
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(SolarOrange),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Rounded.WbSunny, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Gerenciar Energia Solar", color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            // Cards de Configuração (Reutilizando a estrutura que criamos na Cisterna, mas com fundo claro nas opções abaixo)

            SettingsCard(containerColor = CardBackground) {
                SolarToggleRow(icon = { Icon(Icons.Rounded.WbSunny, contentDescription = null, tint = SolarOrange) },
                    title = "Monitoramento Ativo", subtitle = "Acompanhamento em tempo real", isChecked = activeMonitoring) { activeMonitoring = it }
            }

            SettingsCard(containerColor = Color.White) {
                SolarToggleRow(icon = { Icon(Icons.Rounded.NotificationsActive, contentDescription = null, tint = SolarOrange) },
                    title = "Alertas de Baixa Geração", subtitle = "Notificações de eficiência reduzida", isChecked = lowGenerationAlert, isDarkTheme = false) { lowGenerationAlert = it }
            }

            SettingsCard(containerColor = Color.White) {
                SolarToggleRow(icon = { Icon(Icons.Rounded.Eco, contentDescription = null, tint = PrimaryGreen) },
                    title = "Modo Econômico", subtitle = "Otimiza o uso de energia", isChecked = ecoMode, isDarkTheme = false) { ecoMode = it }
            }

            // Slider Meta Diária
            SettingsCard(containerColor = Color.White) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.TrackChanges, contentDescription = null, tint = WaterBlue)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Meta Diária de Geração", color = Color.Black, fontWeight = FontWeight.Bold)
                            Text("Objetivo: ${dailyGoal.toInt()} kWh/dia", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Slider(
                        value = dailyGoal, onValueChange = { dailyGoal = it }, valueRange = 0f..50f,
                        colors = SliderDefaults.colors(thumbColor = PrimaryGreen, activeTrackColor = PrimaryGreen, inactiveTrackColor = DarkBackground.copy(alpha = 0.2f))
                    )
                }
            }

            SettingsCard(containerColor = Color.White) {
                SolarToggleRow(icon = { Icon(Icons.Rounded.WifiTethering, contentDescription = null, tint = PrimaryGreen) },
                    title = "Conectividade", subtitle = "Conexão com os painéis", isChecked = connectivity, isDarkTheme = false) { connectivity = it }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botão Recalibrar
            OutlinedButton(
                onClick = { /* Recalibrate */ },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                border = androidx.compose.foundation.BorderStroke(1.dp, TextSecondary.copy(alpha = 0.3f))
            ) {
                Text("↻ Recalibrar Sistema Solar", color = TextSecondary)
            }

            // Botão Restaurar
            OutlinedButton(
                onClick = { /* Reset */ },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = DarkBackground.copy(alpha = 0.5f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red.copy(alpha = 0.5f))
            ) {
                Text("↺ Restaurar Configurações Padrão", color = Color.Red.copy(alpha = 0.8f))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Componente auxiliar local para as linhas de toggle da energia solar
@Composable
fun SolarToggleRow(
    icon: @Composable () -> Unit, title: String, subtitle: String, isChecked: Boolean, isDarkTheme: Boolean = true, onCheckedChange: (Boolean) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon()
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, color = if (isDarkTheme) TextPrimary else Color.Black, fontWeight = FontWeight.Bold)
                Text(subtitle, color = if (isDarkTheme) TextSecondary else Color.Gray, fontSize = 12.sp)
            }
        }
        Switch(
            checked = isChecked, onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = PrimaryGreen)
        )
    }
}