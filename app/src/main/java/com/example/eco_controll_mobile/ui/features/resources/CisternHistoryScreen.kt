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
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.TrendingDown
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.*
import com.example.eco_controll_mobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CisternHistoryScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
                title = { Text("Lincoln", color = PrimaryGreen, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = TextPrimary)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()), // Permite rolagem caso a tela seja pequena
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cabeçalho
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 16.dp)) {
                Box(
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(WaterBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Rounded.WaterDrop, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Cisterna - Histórico", color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            // Gráfico Simples Customizado
            Card(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nível da Água - Última Semana", color = TextSecondary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    SimpleLineChartMock(modifier = Modifier.fillMaxSize())
                }
            }

            // Cards de Métricas (Lado a Lado)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                MetricCard(
                    title = "Capacidade Total", value = "10.000 L",
                    icon = { Icon(Icons.Rounded.WaterDrop, contentDescription = null, tint = WaterBlue) },
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Consumo Diário", value = "285 L",
                    icon = { Icon(Icons.Rounded.TrendingDown, contentDescription = null, tint = Color(0xFFE57373)) },
                    modifier = Modifier.weight(1f)
                )
                // Card de Consumo de Água Corrigido:
                MetricCard(
                    title = "Consumo Hoje",
                    value = "120 L",
                    icon = { Icon(painterResource(id = R.drawable.water), null, tint = WaterBlue) },
                    modifier = Modifier.weight(1f)
                )
            }

            // Previsão de Autonomia
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Rounded.CalendarToday, contentDescription = null, tint = PrimaryGreen)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Previsão de Autonomia", color = Color.Gray, fontSize = 12.sp)
                    Text("26 dias", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Linha do Tempo
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Linha do Tempo", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                    TimelineItem(date = "20/11/2025", description = "Nível atingiu 100%", dotColor = PrimaryGreen)
                    TimelineItem(date = "18/11/2025", description = "Alerta: Consumo elevado", dotColor = SolarOrange)
                    TimelineItem(date = "15/11/2025", description = "Sistema recalibrado", dotColor = WaterBlue)
                    TimelineItem(date = "10/11/2025", description = "Manutenção realizada", dotColor = TextSecondary)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// --- Componentes Auxiliares desta tela ---

@Composable
fun SimpleLineChartMock(modifier: Modifier = Modifier) {
    // Desenha um gráfico estático usando Canvas do Compose para simular o design
    Canvas(modifier = modifier) {
        val path = Path()
        val width = size.width
        val height = size.height

        // Simulação dos pontos da linha
        path.moveTo(0f, height * 0.2f)
        path.lineTo(width * 0.2f, height * 0.25f)
        path.lineTo(width * 0.4f, height * 0.22f)
        path.lineTo(width * 0.6f, height * 0.3f)
        path.lineTo(width * 0.8f, height * 0.28f)
        path.lineTo(width, height * 0.35f)

        drawPath(
            path = path,
            color = WaterBlue,
            style = Stroke(width = 4.dp.toPx())
        )

        // Eixos pontilhados falsos podem ser adicionados aqui no futuro
    }
}

@Composable
fun MetricCard(title: String, value: String, icon: @Composable () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground) // PADRÃO
    ) {
        // CENTRALIZADO COM ÍCONE AO LADO
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title, color = TextSecondary, fontSize = 10.sp)
                Text(value, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun TimelineItem(date: String, description: String, dotColor: Color) {
    Row(verticalAlignment = Alignment.Top) {
        Box(modifier = Modifier.padding(top = 6.dp).size(8.dp).clip(CircleShape).background(dotColor))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(date, color = Color.Gray, fontSize = 12.sp)
            Text(description, color = Color.Black, fontSize = 14.sp)
        }
    }
}