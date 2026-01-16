package com.example.alfabetizacionrural.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alfabetizacionrural.R

@Composable
fun RegistrationScreen(
    onRegistrationSuccess: () -> Unit,
    viewModel: RegistrationViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Aquí usamos los avatares que creaste
    val avatars = listOf(
        R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3,
        R.drawable.avatar_4, R.drawable.avatar_5, R.drawable.avatar_6
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¡Bienvenido!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Escribe tu nombre y toca un dibujo")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.alias,
            onValueChange = { viewModel.onAliasChange(it) },
            label = { Text("Tu Apodo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Elige tu Avatar:")

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.height(300.dp).fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(avatars) { avatarResId ->
                val simpleId = avatars.indexOf(avatarResId) + 1
                val isSelected = state.selectedAvatarId == simpleId

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(80.dp)
                        .border(
                            width = if (isSelected) 4.dp else 0.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable { viewModel.onAvatarSelected(simpleId) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = avatarResId),
                        contentDescription = "Avatar",
                        modifier = Modifier.size(60.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.registerUser(onSuccess = onRegistrationSuccess) },
            enabled = state.alias.isNotBlank() && state.selectedAvatarId != null,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("COMENZAR")
        }
    }
}