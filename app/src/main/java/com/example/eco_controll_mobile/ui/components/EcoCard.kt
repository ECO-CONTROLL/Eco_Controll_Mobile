package com.example.eco_controll_mobile.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.eco_controll_mobile.ui.theme.CardBackground

@Composable
fun EcoCard(
    modifier: Modifier = Modifier,
    containerColor: Color = CardBackground,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}