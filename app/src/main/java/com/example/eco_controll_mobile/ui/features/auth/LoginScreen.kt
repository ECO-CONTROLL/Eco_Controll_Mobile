package com.example.eco_controll_mobile.ui.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco_controll_mobile.ui.components.EcoTextField
import com.example.eco_controll_mobile.ui.theme.DarkBackground
import com.example.eco_controll_mobile.ui.theme.PrimaryGreen
import androidx.compose.ui.res.painterResource
import com.example.eco_controll_mobile.R

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Box(modifier = Modifier.weight(1.2f).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(120.dp)
                )
                Text(text = "Bem-vindo de volta!", color = Color.White.copy(alpha = 0.7f), fontSize = 16.sp)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().weight(1.8f),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            // Removi o Arrangement.spacedBy para ter controle manual do espaço
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp)) {
                // Campo E-mail
                Text("E-mail", color = DarkBackground, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
                EcoTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "E-mail",
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null, tint = Color.Gray) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Senha
                Text("Senha", color = DarkBackground, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
                EcoTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Senha",
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null, tint = Color.Gray) },
                    isPassword = true
                )

                // Esqueci minha senha
                TextButton(
                    onClick = { /* Ação de esqueci a senha */ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Esqueci minha senha", color = Color(0xFF5D88A5))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botão Entrar
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Entrar", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                // O SEGREDO ESTÁ AQUI: Essa mola empurra o "Criar conta" para o rodapé
                Spacer(modifier = Modifier.weight(1f))

                // Criar conta (Sempre visível no fundo do card)
                TextButton(
                    onClick = { /* Navegar para cadastro */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Não tem uma conta? ", color = Color.Gray)
                    Text("Criar conta", color = Color(0xFF5D88A5), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}