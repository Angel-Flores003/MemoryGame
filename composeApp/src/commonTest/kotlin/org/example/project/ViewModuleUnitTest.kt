package org.example.project

import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.viewModel.VMGameMenu
import org.example.project.viewModel.VMGameScreen
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class ViewModuleUnitTest {
    // Definimos la variable fuera para que setup() la pueda inicializar
    private lateinit var viewModelSceen: VMGameScreen
    private lateinit var viewModelMenu: VMGameMenu

    @BeforeTest // En KMP se usa BeforeTest de kotlin.test
    fun setup() {
        viewModelSceen = VMGameScreen()
        viewModelMenu = VMGameMenu()
    }

    @Test
    fun initialState() {
        // Comprobamos que al empezar todo esté a cero o vacío
        //Gscreen
        assertEquals(0, viewModelSceen.listPlayers.size)
        assertEquals(false, viewModelSceen.interactuable)
        assertEquals(0, viewModelSceen.indiceJugadorActual)
        //Gmenu
        assertNotNull(viewModelMenu.playerOptions)
        assertNotNull(viewModelMenu.difficultyOptions)
    }

    @Test
    fun testCounterIncrement() {
        // Ejemplo de lógica: si llamamos a incrementar, ¿suma 1?
        // viewModel.incrementCounter()
        // assertEquals(1, viewModel.counterValue.value)
    }
}