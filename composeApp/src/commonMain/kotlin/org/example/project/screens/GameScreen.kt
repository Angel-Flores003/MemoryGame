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
import kotlinproject.composeapp.generated.resources.acheron
import kotlinproject.composeapp.generated.resources.aglaea
import kotlinproject.composeapp.generated.resources.ashveil
import kotlinproject.composeapp.generated.resources.aventurine
import kotlinproject.composeapp.generated.resources.bailu
import kotlinproject.composeapp.generated.resources.black_swan
import kotlinproject.composeapp.generated.resources.castorice
import kotlinproject.composeapp.generated.resources.ceridra
import kotlinproject.composeapp.generated.resources.cipher
import kotlinproject.composeapp.generated.resources.clara
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.cyrene
import kotlinproject.composeapp.generated.resources.eve
import kotlinproject.composeapp.generated.resources.firefly
import kotlinproject.composeapp.generated.resources.fu_xuan
import kotlinproject.composeapp.generated.resources.gallagher
import kotlinproject.composeapp.generated.resources.himeko
import kotlinproject.composeapp.generated.resources.hsrlogo
import kotlinproject.composeapp.generated.resources.huohuo
import kotlinproject.composeapp.generated.resources.hya
import kotlinproject.composeapp.generated.resources.hysilens
import kotlinproject.composeapp.generated.resources.jiaoqiu
import kotlinproject.composeapp.generated.resources.jingliu
import kotlinproject.composeapp.generated.resources.kafka
import kotlinproject.composeapp.generated.resources.ling
import kotlinproject.composeapp.generated.resources.moze
import kotlinproject.composeapp.generated.resources.mydei
import kotlinproject.composeapp.generated.resources.phainon
import kotlinproject.composeapp.generated.resources.rappa
import kotlinproject.composeapp.generated.resources.terreneitor
import kotlinproject.composeapp.generated.resources.the_herta
import kotlinproject.composeapp.generated.resources.tribbi
import kotlinproject.composeapp.generated.resources.wolfnine
import kotlinproject.composeapp.generated.resources.yao_guang
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.data.local.Carta
import org.example.project.navigation.Route
import org.example.project.viewModel.MainViewModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameScreen(
    navigateToResults: () -> Unit,
    selectedOption: String,
    selectedOption2: String,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("P2")
        viewModel.ScreenGameByDificulty((selectedOption2))

        Button(onClick = { navigateToResults() },
            modifier = Modifier.width(150.dp)) {
            Text(
                "Go Results",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun GameGrid(rows: Int, cols: Int) {
    val scope = rememberCoroutineScope()

    // 1. Generamos el mazo de cartas solo cuando cambia el tamaño del juego
    val totalCartas = rows * cols
    val mazoInicial = remember(rows, cols) {
        val imagenes = listOf(
            Res.drawable.eve,
            Res.drawable.acheron,
            Res.drawable.ling,
            Res.drawable.cyrene,
            Res.drawable.castorice,
            Res.drawable.wolfnine,
            Res.drawable.jingliu,
            Res.drawable.clara,
            Res.drawable.phainon,
            Res.drawable.rappa,
            Res.drawable.yao_guang,
            Res.drawable.firefly,
            Res.drawable.fu_xuan,
            Res.drawable.himeko,
            Res.drawable.black_swan,
            Res.drawable.gallagher,
            Res.drawable.the_herta,
            Res.drawable.aventurine,
            Res.drawable.hysilens,
            Res.drawable.mydei,
            Res.drawable.jiaoqiu,
            Res.drawable.aglaea,
            Res.drawable.tribbi,
            Res.drawable.terreneitor,
            Res.drawable.bailu,
            Res.drawable.kafka,
            Res.drawable.huohuo,
            Res.drawable.hya,
            Res.drawable.ashveil,
            Res.drawable.cipher,
            Res.drawable.ceridra,
            Res.drawable.moze,
        )

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
                                            scope.launch {
                                                delay(1000)
                                                mazoInicial[mazoInicial.indexOf(carta1)] = carta1.copy(estaEmparejada = true)
                                                mazoInicial[mazoInicial.indexOf(carta2)] = carta2.copy(estaEmparejada = true)
                                            }
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