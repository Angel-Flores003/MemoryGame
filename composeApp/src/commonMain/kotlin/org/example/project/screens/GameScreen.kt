package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.viewModel.VMGameScreen
import kotlin.collections.get

@Composable
fun GameScreen(
    navigateToResults: () -> Unit,
    navigateBack: () -> Unit,
    player: String,
    difficulty: String,
    vm: VMGameScreen
) {
    // Calculamos filas y columnas basándonos en la dificultad
    val (rows, cols) = when (difficulty) {
        "Tutorial" -> 2 to 2
        "Easy" -> 4 to 2
        "Medium" -> 4 to 4
        "Hard" -> 8 to 4
        "Insane" -> 8 to 8
        else -> 4 to 4
    }

//    val listPlayers = when (player) {
//        "VS 1" -> listOf("P1")
//        "1 VS 1" -> listOf("P1", "P2")
//        "VS 3" -> listOf("P1", "P2", "P3")
//        "2 VS 2" -> listOf("P1", "P3", "P2", "P4")
//        "VS 4" -> listOf("P1", "P2", "P3", "P4")
//        else -> listOf("Player")
//    }
//
//    var indiceJugadorActual by remember { mutableStateOf(0) }
//
//    // Variable calculada para mostrar el nombre fácilmente
//    val nombreTurnoActual = listPlayers[indiceJugadorActual]

    // Inicializar el juego solo una vez al entrar
    LaunchedEffect(difficulty) {
        vm.prepararJuego(rows, cols)
        vm.configurePlayers(player)
    }

    val turnoActual = vm.nombreTurnoActual

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = navigateBack) { Text("Go back") }
            Text("Turno de: ${turnoActual}")
            Text(text = player, style = MaterialTheme.typography.headlineSmall)
        }

        GameGrid(
            rows = rows,
            cols = cols,
            vm = vm,
            onVictoria = navigateToResults
        )
    }
}

@Composable
fun GameGrid(
    rows: Int,
    cols: Int,
    vm: VMGameScreen,
    onVictoria: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        repeat(rows) { rowIndex ->
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                repeat(cols) { colIndex ->
                    val index = rowIndex * cols + colIndex

                    // Verificamos que el índice existe para evitar errores de carga
                    if (index < vm.mazo.size) {
                        val carta = vm.mazo[index]

                        Box(modifier = Modifier.weight(1f).padding(4.dp)) {
                            if (!carta.estaEmparejada) {
                                CartaCard(
                                    carta = carta,
                                    onClick = { vm.onCartaClicked(index, onVictoria) }
                                )
                            } else {
                                // Hueco vacío si ya se emparejó
                                Spacer(Modifier.fillMaxSize())
                            }
                        }
                    }
                }
            }
        }
    }
}