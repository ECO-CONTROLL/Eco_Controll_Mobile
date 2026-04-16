package com.example.eco_controll_mobile.ui.features.resources

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarHistoryScreen(onNavigateBack: () -> Unit) {
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
            // Cabeçalho
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 16.dp)) {
                Box(
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(SolarOrange),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Rounded.WbSunny, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Energia Solar - Histórico", color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            // Gráfico de Barras
            Card(
                modifier = Modifier.fillMaxWidth().height(220.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFC5BAA1)) // Tom de bege do design
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Geração de Energia - Última Semana", color = DarkBackground, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    SimpleBarChartMock(modifier = Modifier.fillMaxSize())
                }
            }

            // Novos Cards de Energia
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MetricCard(
                    modifier = Modifier.weight(1f),
                    title = "Produção Total",
                    value = "15.4 kWh",
                    icon = { Icon(Icons.Default.Bolt, null, tint = SolarOrange) }
                )
                MetricCard(
                    modifier = Modifier.weight(1f),
                    title = "Geração do Dia",
                    value = "4.2 kWh",
                    icon = { Icon(Icons.Default.WbSunny, null, tint = SolarOrange) }
                )
            }

            // Status das Placas Solares
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Status das Placas Solares", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                    SolarPanelStatusRow("Painel 1", "3.2 kW")
                    SolarPanelStatusRow("Painel 2", "3.3 kW")
                    SolarPanelStatusRow("Painel 3", "3.4 kW")
                    SolarPanelStatusRow("Painel 4", "3.5 kW")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SimpleBarChartMock(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val barWidth = 24.dp.toPx()
        val spacing = (size.width - (barWidth * 7)) / 6 // 7 dias
        val heights = listOf(0.6f, 0.75f, 0.8f, 0.7f, 0.85f, 0.8f, 0.75f) // Alturas relativas

        heights.forEachIndexed { index, heightRatio ->
            val barHeight = size.height * heightRatio
            val xOffset = index * (barWidth + spacing)
            val yOffset = size.height - barHeight

            drawRoundRect(
                color = SolarOrange,
                topLeft = Offset(xOffset, yOffset),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
            )
        }
    }
}

@Composable
fun SolarPanelStatusRow(name: String, power: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(PrimaryGreen))
            Spacer(modifier = Modifier.width(12.dp))
            Text(name, color = Color.DarkGray, fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(power, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Operacional", color = Color.Gray, fontSize = 10.sp)
        }
    }
}