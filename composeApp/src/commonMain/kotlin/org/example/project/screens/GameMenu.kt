package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.viewModel.VMGameMenu

@Composable
fun GameMenu(
    navigateBack: () -> Unit,
    navigateToGameScreen: (players: String, difficulty: String) -> Unit,
    vm: VMGameMenu
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Text("Memory Game", style = MaterialTheme.typography.displaySmall)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dropdown de Jugadores
            MenuSelector(
                label = "Players",
                options = vm.playerOptions,
                selectedOption = vm.selectedPlayers,
                onOptionSelected = { vm.changePlayers(it) }
            )

            Spacer(Modifier.height(16.dp))

            // Dropdown de Dificultad
            MenuSelector(
                label = "Difficulty",
                options = vm.difficultyOptions,
                selectedOption = vm.selectedDifficulty,
                onOptionSelected = { vm.changeDifficulty(it) }
            )

            Spacer(Modifier.height(32.dp))

            // Botón PLAY
            Button(
                onClick = { navigateToGameScreen(vm.selectedPlayers, vm.selectedDifficulty) },
                modifier = Modifier.width(200.dp).height(50.dp)
            ) {
                Text("Play", style = MaterialTheme.typography.titleLarge)
            }
        }

        // PARTE INFERIOR
        Button(
            onClick = navigateBack,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Back")
        }
    }
}

// Componente reutilizable para los desplegables
@Composable
fun MenuSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Box {
            Button(
                onClick = { expanded = true },
                modifier = Modifier.width(200.dp)
            ) {
                Text(selectedOption)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}