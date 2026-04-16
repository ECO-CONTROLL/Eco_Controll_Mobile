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
        topBar = { TopBarProfile(onProfileClick = onNavigateToProfile) }
    ) { paddingValues ->
        // CORREÇÃO: Adicionado verticalScroll para permitir arrastar para baixo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()), // Ativa o scroll
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Card Cisterna
            ResourceCard(
                title = "Cisterna",
                value = "75%",
                icon = { Icon(Icons.Rounded.WaterDrop, contentDescription = null, tint = WaterBlue) },
                onInfoClick = onNavigateToHistoryCistern,
                onManageClick = onNavigateToManageCistern
            )

            // Card Energia Solar
            ResourceCard(
                title = "Energia Solar",
                value = "12.5 kWh",
                icon = { Icon(Icons.Rounded.WbSunny, contentDescription = null, tint = SolarOrange) },
                onInfoClick = onNavigateToHistorySolar,
                onManageClick = onNavigateToManageSolar
            )

            Text("Acesso Rápido", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)

            // Linha de Atalhos (Acesso Rápido)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                QuickAccessCard("Configurações", Icons.Default.Settings, Modifier.weight(1f), onClick = onNavigateToSettings)
                QuickAccessCard("Relatórios", Icons.Default.Description, Modifier.weight(1f), onClick = onNavigateToReports)
                QuickAccessCard("Perfil", Icons.Default.Person, Modifier.weight(1f), onClick = onNavigateToProfile)
            }

            // Status do Sistema
            SystemStatusCard()

            // CONTEÚDO EXTRA (Para testar o scroll)
            Text("Dicas de Sustentabilidade", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)

            // Gerando cards aleatórios para provar que a tela rola
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
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarProfile(onProfileClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
        title = {
            // CENTRALIZADO
            Column(
                modifier = Modifier.fillMaxWidth().clickable { onProfileClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(45.dp).clip(CircleShape).background(Color.Gray))
                Text(text = "Lincoln", color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    )
}

@Composable
fun ResourceCard(title: String, value: String, icon: @Composable () -> Unit, onInfoClick: () -> Unit, onManageClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(50.dp).clip(RoundedCornerShape(12.dp)).background(DarkBackground), contentAlignment = Alignment.Center) { icon() }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(title, color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Text(value, color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onInfoClick, colors = ButtonDefaults.buttonColors(containerColor = TextSecondary.copy(alpha = 0.2f)), modifier = Modifier.weight(1f)) { Text("Histórico") }
                Button(onClick = onManageClick, colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen), modifier = Modifier.weight(1f)) { Text("Gerenciar") }
            }
        }
    }
}

@Composable
fun QuickAccessCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.height(100.dp).clickable { onClick() }, // AQUI O CLIQUE FUNCIONA
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

@Composable
fun SystemStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground) // MUDADO PARA PADRÃO
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Status do Sistema", fontWeight = FontWeight.Bold, color = TextPrimary)
            StatusRow("Última atualização", "Agora")
            StatusRow("Economia do mês", "R$ 287,50", PrimaryGreen)
            StatusRow("CO₂ evitado", "156 kg", PrimaryGreen)
        }
    }
}

@Composable
fun StatusRow(label: String, value: String, color: Color = Color.Black) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray)
        Text(value, color = color, fontWeight = FontWeight.Bold)
    }
}