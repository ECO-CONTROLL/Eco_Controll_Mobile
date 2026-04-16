package com.example.eco_controll_mobile.ui.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun HomeScreen(
    onNavigateToManageCistern: () -> Unit,
    onNavigateToHistoryCistern: () -> Unit,
    onNavigateToManageSolar: () -> Unit,
    onNavigateToHistorySolar: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToReports: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopBarProfile(
                onProfileClick = onNavigateToProfile,
                onNotificationClick = { /* Ação de notificação no futuro */ }
            )
        }
    ) { paddingValues ->
        // CORREÇÃO: Uma única Column abraçando toda a tela
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp) // Espaçamento automático entre os blocos
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // 1. CARDS DE CISTERNA E SOLAR PRIMEIRO
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CisternCard(
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToHistoryCistern
                )
                SolarCard(
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToHistorySolar
                )
            }

            // 2. CARDS DE ECONOMIA E CO2 EMBAIXO
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                EconomyCard(modifier = Modifier.weight(1f))
                CO2Card(modifier = Modifier.weight(1f))
            }

            // 3. ACESSO RÁPIDO
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Acesso Rápido", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                    QuickAccessCard("Configurações", Icons.Default.Settings, Modifier.weight(1f), onClick = onNavigateToSettings)
                    QuickAccessCard("Relatórios", Icons.Default.Description, Modifier.weight(1f), onClick = onNavigateToReports)
                    QuickAccessCard("Perfil", Icons.Default.Person, Modifier.weight(1f), onClick = onNavigateToProfile)
                }
            }

            // 4. DICAS DE SUSTENTABILIDADE
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Dicas de Sustentabilidade", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                repeat(3) { index ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = CardBackground),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = PrimaryGreen)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                "Dica #${index + 1}: Reduza o consumo de energia em horários de pico para aumentar sua economia.",
                                color = TextSecondary, fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarProfile(onProfileClick: () -> Unit, onNotificationClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
        title = {
            Column(
                modifier = Modifier.fillMaxWidth().clickable { onProfileClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(45.dp).clip(CircleShape).background(Color.Gray))
                Text(text = "Lincoln", color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificações",
                    tint = PrimaryGreen
                )
            }
        }
    )
}

@Composable
fun QuickAccessCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.height(100.dp).clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, contentDescription = null, tint = TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, color = TextSecondary, fontSize = 12.sp)
        }
    }
}

// --- CRIADOS OS COMPONENTES PARA OS NOVOS CARDS ---

@Composable
fun CisternCard(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    MiniResourceCard(title = "Cisterna", value = "80%", icon = Icons.Rounded.WaterDrop, iconTint = WaterBlue, modifier = modifier, onClick = onClick)
}

@Composable
fun SolarCard(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    MiniResourceCard(title = "Solar", value = "4.2 kWh", icon = Icons.Rounded.WbSunny, iconTint = SolarOrange, modifier = modifier, onClick = onClick)
}

@Composable
fun EconomyCard(modifier: Modifier = Modifier) {
    MiniResourceCard(title = "Economia", value = "R$ 150", icon = Icons.Default.AttachMoney, iconTint = PrimaryGreen, modifier = modifier, onClick = {})
}

@Composable
fun CO2Card(modifier: Modifier = Modifier) {
    MiniResourceCard(title = "CO2 Salvo", value = "12 kg", icon = Icons.Default.Eco, iconTint = PrimaryGreen, modifier = modifier, onClick = {})
}

@Composable
fun MiniResourceCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, iconTint: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, color = TextSecondary, fontSize = 14.sp)
            Text(value, color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}