package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.viewModel.VMGameMenu
import org.example.project.viewModel.VMGameScreen
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull


class ViewModuleUnitTest {
    // Definimos la variable fuera para que setup() la pueda inicializar
    private lateinit var viewModelSceen: VMGameScreen
    private lateinit var viewModelMenu: VMGameMenu

    @BeforeTest // En KMP se usa BeforeTest de kotlin.test
    fun setup() {
        viewModelSceen = VMGameScreen()
        viewModelSceen.prepararJuego(2, 2)//Juego modo tutorial
        viewModelSceen.configurePlayers("1 VS 1")
        viewModelMenu = VMGameMenu()
    }

    @Test
    fun initialState() {
        // Comprobamos que al empezar todo esté a cero o vacío
        //Gscreen
        assertEquals(true, viewModelSceen.interactuable)
        assertEquals(0, viewModelSceen.indiceJugadorActual)
        //Gmenu
        assertNotNull(viewModelMenu.playerOptions)
        assertNotNull(viewModelMenu.difficultyOptions)
    }

    @Test
    fun checkDeckSize() {
        assertEquals(4, viewModelSceen.mazo.size)
    }

    @Test
    fun turnChangesOnMiss() {
        val jugadorInicial = viewModelSceen.indiceJugadorActual

        viewModelSceen.changeTurn()

        val jugadorFinal = viewModelSceen.indiceJugadorActual
        assertNotEquals(jugadorInicial, jugadorFinal)
    }

    @Test
    fun pointsIncreaseOnMatch() {
        val primerJugador = viewModelSceen.listPlayers[0]
        val startPoints = viewModelSceen.pointsByPlayer[primerJugador] ?: 0

        viewModelSceen.sumPointsCurrentPlayer()

        val finalPoints = viewModelSceen.pointsByPlayer[primerJugador] ?: 0

        assertEquals(startPoints + 1, finalPoints,)
    }

    @Test
    fun victoryDetected() {
        viewModelSceen.mazo.forEachIndexed { index, carta ->
            viewModelSceen.mazo[index] = carta.copy(estaEmparejada = true)
        }
        val todasEmparejadas = viewModelSceen.mazo.all { it.estaEmparejada }
        assertEquals(true, todasEmparejadas)
    }
}