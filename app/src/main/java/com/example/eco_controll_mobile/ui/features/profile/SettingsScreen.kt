package com.example.eco_controll_mobile.ui.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Straighten
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
fun SettingsScreen(onNavigateBack: () -> Unit) {

    // Estados fictícios para os toggles
    var lowLevelAlert by remember { mutableStateOf(true) }
    var weeklyReports by remember { mutableStateOf(false) }
    var monthlyEconomy by remember { mutableStateOf(true) }

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
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 1. Notificações
            SettingsSectionCard(title = "Notificações", icon = { Icon(Icons.Rounded.Notifications, contentDescription = null, tint = PrimaryGreen) }) {
                SwitchSettingRow("Alertas de nível baixo", lowLevelAlert) { lowLevelAlert = it }
                SwitchSettingRow("Relatórios semanais", weeklyReports) { weeklyReports = it }
                SwitchSettingRow("Economia mensal", monthlyEconomy) { monthlyEconomy = it }
            }

            // 2. Unidades de Medida
            SettingsSectionCard(title = "Unidades de Medida", icon = { Icon(Icons.Rounded.Straighten, contentDescription = null, tint = PrimaryGreen) }) {
                ChevronSettingRow("Capacidade", "Litros (L)")
                ChevronSettingRow("Energia", "Kilowatt-hora (kWh)")
                ChevronSettingRow("Temperatura", "Celsius (°C)")
            }

            // 3. Integração IoT
            SettingsSectionCard(title = "Integração IoT", icon = { Icon(Icons.Rounded.WifiTethering, contentDescription = null, tint = PrimaryGreen) }) {
                IoTStatusRow("Sensor de cisterna", true)
                IoTStatusRow("Inversor solar", true)
                IoTStatusRow("Gateway principal", true)
            }

            // 4. Ajuda e Suporte
            SettingsSectionCard(title = "Ajuda e Suporte", icon = { Icon(Icons.Rounded.HelpOutline, contentDescription = null, tint = Color.Gray) }) {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Ajuda e Suporte", color = Color.Black)
                    Icon(Icons.Rounded.ChevronRight, contentDescription = null, tint = Color.Gray)
                }
            }

            // 5. Botão Sair
            OutlinedButton(
                onClick = { /* Logout */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = DarkBackground.copy(alpha = 0.5f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red.copy(alpha = 0.2f))
            ) {
                Text("[→  Sair da Conta", color = Color.Red.copy(alpha = 0.8f))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// --- Componentes Auxiliares de Configurações ---

@Composable
fun SettingsSectionCard(title: String, icon: @Composable () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
                icon()
                Spacer(modifier = Modifier.width(12.dp))
                Text(title, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            content()
        }
    }
}

@Composable
fun SwitchSettingRow(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color.DarkGray, fontSize = 14.sp)
        Switch(
            checked = isChecked, onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = CardBackground)
        )
    }
}

@Composable
fun ChevronSettingRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().clickable {}.padding(vertical = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color.DarkGray, fontSize = 14.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(value, color = Color.Gray, fontSize = 12.sp)
            Icon(Icons.Rounded.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}

@Composable
fun IoTStatusRow(deviceName: String, isConnected: Boolean) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(if (isConnected) PrimaryGreen else Color.Red))
            Spacer(modifier = Modifier.width(12.dp))
            Text(deviceName, color = Color.DarkGray, fontSize = 14.sp)
        }
        Text(if (isConnected) "Conectado" else "Desconectado", color = if (isConnected) PrimaryGreen else Color.Red, fontSize = 12.sp)
    }
}