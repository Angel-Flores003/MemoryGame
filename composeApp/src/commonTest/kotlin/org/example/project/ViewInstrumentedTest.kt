package org.example.project

import org.example.project.viewModel.VMGameMenu
import org.example.project.viewModel.VMGameScreen
import kotlin.test.Test/*
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4*/

//@RunWith(AndroidJUnit4::class)
class ViewInstrumentedTest {
    /*@get:Rule // <--- Es obligatorio usar @get:Rule en Kotlin para la regla
    val composeTestRule = createComposeRule()*/

    @Test
    fun myView_Menu_Screen() {
        // Inicializamos ViewModels (puedes usar mocks si prefieres)
        val viewModelScreen = VMGameScreen()
        val viewModelMenu = VMGameMenu()

        /*composeTestRule.setContent {
            // Tu función de UI que dibujas en la pantalla
            MyViewScreen(viewModel = viewModelScreen)
        }

        // 1. Comprobamos que el botón de Reset existe
        composeTestRule.onNodeWithText("Reset").assertExists()*/

        // 2. Ejemplo: simular un click
        // composeTestRule.onNodeWithText("Reset").performClick()
    }
}