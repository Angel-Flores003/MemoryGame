package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GameMenu(navigateBack: () -> Unit, navigateToGameScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Elegir cantidad jugadores
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("VS 1", "1 VS 1", "VS 3", "2 VS 2", "VS 4")
        var selectedOption by remember { mutableStateOf(options[0]) }
        //Elegir dificultad
        var expanded2 by remember {mutableStateOf(false)}
        val options2 = listOf("Tutorial", "Easy", "Medium", "Hard", "Insane")//4, 8, 16, 32, 64
        var selectedOption2 by remember {mutableStateOf(options2[0])}

        // Elegir cantidad jugadores
        Box {
            Button(onClick = { expanded = true }, modifier = Modifier.width(150.dp)) {
                Text(
                    selectedOption,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }
        //Elegir dificultad
        Spacer(Modifier.height(8.dp))
        Box {
            Button(onClick = { expanded2 = true }, modifier = Modifier.width(150.dp)) {
                Text(
                    selectedOption2,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            DropdownMenu(expanded = expanded2, onDismissRequest = { expanded2 = false }) {
                options2.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption2 = option
                            expanded2 = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))
        Button(onClick = { navigateToGameScreen(selectedOption, selectedOption2) }, modifier = Modifier.width(150.dp)) {
            Text(
                "Play",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Button(onClick = navigateBack) { Text("Back") }
        Spacer(Modifier.height(25.dp))
    }
}