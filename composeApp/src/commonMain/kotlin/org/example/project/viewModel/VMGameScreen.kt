package org.example.project.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinproject.composeapp.generated.resources.cyrene
import kotlinproject.composeapp.generated.resources.eve
import kotlinproject.composeapp.generated.resources.firefly
import kotlinproject.composeapp.generated.resources.fu_xuan
import kotlinproject.composeapp.generated.resources.gallagher
import kotlinproject.composeapp.generated.resources.himeko
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
import org.example.project.screens.GameGrid

class VMGameScreen : ViewModel() {
    var mazo = mutableStateListOf<Carta>()
        private set

    // Control de clics
    var interactuable by mutableStateOf(true)
        private set

    private val todasLasImagenes = listOf(
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
        Res.drawable.moze
    )



    fun prepararJuego(rows: Int, cols: Int) {
        val totalCartas = rows * cols
        val seleccionadas = todasLasImagenes.take(totalCartas / 2)
        val mazoMezclado = (seleccionadas + seleccionadas)
            .shuffled()
            .mapIndexed { index, res -> Carta(id = index, imagenFrontal = res) }

        mazo.clear()
        mazo.addAll(mazoMezclado)
        interactuable = true
    }

    fun onCartaClicked(index: Int, onVictoria: (String) -> Unit) {mazo
        val carta = mazo[index]

        if (interactuable && !carta.estaBocaArriba && !carta.estaEmparejada) {
            // 1. Girar la carta seleccionada
            mazo[index] = carta.copy(estaBocaArriba = true)

            // 2. Filtrar cartas que están boca arriba pero no emparejadas todavía
            val giradas = mazo.filter { it.estaBocaArriba && !it.estaEmparejada }

            if (giradas.size == 2) {
                interactuable = false
                val carta1 = giradas[0]
                val carta2 = giradas[1]

                viewModelScope.launch {
                    delay(1000)
                    if (carta1.imagenFrontal == carta2.imagenFrontal) {
                        // ¡Match!
                        actualizarCarta(carta1, estaEmparejada = true)
                        actualizarCarta(carta2, estaEmparejada = true)
                        sumPointsCurrentPlayer()//suma puntos al jugador actual
                    } else {
                        // No Match
                        actualizarCarta(carta1, estaBocaArriba = false)
                        actualizarCarta(carta2, estaBocaArriba = false)
                        changeTurn()//cambia el turno
                    }

                    interactuable = true

                    // Comprobar victoria
                    if (mazo.all { it.estaEmparejada }) {
                        val results = getResultsOrderbuPoints()
                        onVictoria(results)
                    }
                }
            }
        }
    }

    private fun actualizarCarta(carta: Carta, estaBocaArriba: Boolean = carta.estaBocaArriba, estaEmparejada: Boolean = carta.estaEmparejada) {
        val index = mazo.indexOfFirst { it.id == carta.id }
        if (index != -1) {
            mazo[index] = mazo[index].copy(estaBocaArriba = estaBocaArriba, estaEmparejada = estaEmparejada)
        }
    }



    var listPlayers by mutableStateOf(listOf<String>())
        private set

    var indiceJugadorActual by mutableStateOf(0)
        private set

    var pointsByPlayer by mutableStateOf(mapOf<String, Int>())
        private set

    fun configurePlayers(player: String) {
        listPlayers = when (player) {
            "VS 1" -> listOf("P1")
            "1 VS 1" -> listOf("P1", "P2")
            "VS 3" -> listOf("P1", "P2", "P3")
            "2 VS 2" -> listOf("P1", "P3", "P2", "P4")
            "VS 4" -> listOf("P1", "P2", "P3", "P4")
            else -> listOf("Player")
        }
        pointsByPlayer = listPlayers.associateWith { 0 }
        indiceJugadorActual = 0
    }

    fun changeTurn() {
        if (listPlayers.isNotEmpty()) {
            indiceJugadorActual = (indiceJugadorActual + 1) % listPlayers.size
        }
    }

    // Propiedad calculada segura
    val nombreTurnoActual: String
        get() = listPlayers.getOrElse(indiceJugadorActual) { "Cargando..." }

    fun sumPointsCurrentPlayer() {
        val currentName = listPlayers[indiceJugadorActual]
        val currentPoints = pointsByPlayer[currentName] ?: 0
        //actualizar el mapa de puntos
        pointsByPlayer = pointsByPlayer + (currentName to currentPoints + 1)
    }

    fun getResultsOrderbuPoints(): String {
        return pointsByPlayer.entries
            .sortedByDescending { it.value }
            .joinToString(separator = ",") {"${it.key}:${it.value}"}
    }
}