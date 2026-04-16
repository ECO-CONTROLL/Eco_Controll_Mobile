package com.example.eco_controll_mobile.ui.features.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(onNavigateBack: () -> Unit) {
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
            // Card Título Superior
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Relatórios", color = DarkBackground, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Acompanhe o histórico de consumo e geração", color = Color.Gray, fontSize = 14.sp)
                }
            }

            // Lista de Relatórios
            ReportItemCard(title = "Relatório Mensal - Novembro 2025", date = "01/11/2025 - 30/11/2025", type = "Completo", size = "2.4 MB")
            ReportItemCard(title = "Relatório Mensal - Outubro 2025", date = "01/10/2025 - 31/10/2025", type = "Completo", size = "2.1 MB")
            ReportItemCard(title = "Relatório Anual - 2024", date = "01/01/2024 - 31/12/2024", type = "Anual", size = "15.8 MB")

            Spacer(modifier = Modifier.weight(1f, fill = false))

            // Ação Base: Gerar Novo Relatório
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.TrendingUp, contentDescription = null, tint = PrimaryGreen)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Gerar Novo Relatório", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Crie relatórios personalizados com período e dados específicos", color = TextSecondary, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* Gerar */ },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Criar Relatório", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun ReportItemCard(title: String, date: String, type: String, size: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, color = DarkBackground, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.CalendarToday, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(date, color = Color.Gray, fontSize = 12.sp)
                    }
                }
                IconButton(onClick = { /* Download action */ }, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Rounded.Download, contentDescription = "Baixar", tint = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Tipo: $type", color = Color.Gray, fontSize = 12.sp)
                Text(size, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}