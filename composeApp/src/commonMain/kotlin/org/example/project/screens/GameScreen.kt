package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.eve
import kotlinproject.composeapp.generated.resources.hsrlogo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.data.local.Carta
import org.example.project.navigation.Route
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameScreen(
    navigateToResults: () -> Unit,
    selectedOption: String,
    selectedOption2: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var score by remember { mutableStateOf(false) }

        when (selectedOption2) {
            "Tutorial" -> GameGrid(2, 2)
            "Easy"     -> GameGrid(4, 2)
            "Medium"   -> GameGrid(4, 4)
            "Hard"     -> GameGrid(8, 4)
            "Insane"   -> GameGrid(8, 8)
        }

        Button(onClick = { navigateToResults() },
            modifier = Modifier.width(150.dp)) {
            Text(
                "Go Results",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/*@Composable
fun GameGrid(rows: Int, cols: Int, ns: Int) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        repeat(rows) {
            Row(
                modifier = Modifier
                    .weight(1f) // Esto hace que cada fila mida lo mismo de alto
                    .fillMaxWidth()
            ) {
                repeat(cols) {
                    // Cada carta debe tener weight(1f) para repartirse el ancho
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp) // Espaciado entre cartas
                    ) {
                        CartaCard()
                    }
                }
            }
        }
    }
}*/

@Composable
fun GameGrid(rows: Int, cols: Int) {
    val scope = rememberCoroutineScope()

    // 1. Generamos el mazo de cartas solo cuando cambia el tamaño del juego
    // Para este ejemplo, usamos hsr y eve. En juegos más grandes deberías tener una lista más larga.
    val totalCartas = rows * cols
    val mazoInicial = remember(rows, cols) {
        val imagenes = listOf(Res.drawable.compose_multiplatform, Res.drawable.eve) // Añade más aquí

        // Tomamos las imágenes necesarias (totalCartas / 2) y las duplicamos
        val seleccionadas = imagenes.take(totalCartas / 2)
        (seleccionadas + seleccionadas)
            .shuffled()
            .mapIndexed { index, res -> Carta(id = index, imagenFrontal = res) }
            .toMutableStateList()
    }

    // 2. Lógica para controlar qué cartas están seleccionadas
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        repeat(rows) { rowIndex ->
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                repeat(cols) { colIndex ->
                    val index = rowIndex * cols + colIndex
                    val carta = mazoInicial[index]

                    Box(modifier = Modifier.weight(1f).padding(4.dp)) {
                        CartaCard(
                            carta = carta,
                            onClick = {
                                // Lógica del juego al hacer click
                                if (!carta.estaBocaArriba && !carta.estaEmparejada) {
                                    // 1. Girar la carta
                                    mazoInicial[index] = carta.copy(estaBocaArriba = true)

                                    // 2. Comprobar si hay otra carta girada
                                    val giradas = mazoInicial.filter { it.estaBocaArriba && !it.estaEmparejada }

                                    if (giradas.size == 2) {
                                        val carta1 = giradas[0]
                                        val carta2 = giradas[1]

                                        if (carta1.imagenFrontal == carta2.imagenFrontal) {
                                            // ¡MATCH! Las marcamos como encontradas
                                            mazoInicial[mazoInicial.indexOf(carta1)] = carta1.copy(estaEmparejada = true)
                                            mazoInicial[mazoInicial.indexOf(carta2)] = carta2.copy(estaEmparejada = true)
                                        } else {
                                            // NO MATCH: Esperamos un poco y las giramos de vuelta
                                            scope.launch {
                                                delay(1000)
                                                mazoInicial[mazoInicial.indexOf(carta1)] = carta1.copy(estaBocaArriba = false)
                                                mazoInicial[mazoInicial.indexOf(carta2)] = carta2.copy(estaBocaArriba = false)
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}