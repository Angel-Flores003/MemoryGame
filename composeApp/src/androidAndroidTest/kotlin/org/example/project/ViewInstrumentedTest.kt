package org.example.project

@RunWith(AndroidJUnit4::class)
class ViewInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testClickPlayNavigate() {
        // 1. Iniciamos la pantalla del menú
        composeTestRule.setContent {
            GameMenu(
                navigateBack = {},
                navigateToGameScreen = { _, _ -> },
                vm = VMGameMenu()
            )
        }

        // 2. Buscamos el botón "PLAY" y hacemos clic
        composeTestRule.onNodeWithText("PLAY").performClick()

        // 3. Verificamos que algo pasó (por ejemplo, que el botón ya no está
        // o que aparece la cuadrícula de juego si lo haces en un NavHost)
        composeTestRule.onNodeWithText("PLAY").assertDoesNotExist()
    }

    @Test
    fun testDifficultySelectionChangesText() {
        val vm = VMGameMenu()

        // 1. Cargamos el menú
        composeTestRule.setContent {
            GameMenu(
                navigateBack = {},
                navigateToGameScreen = { _, _ -> },
                vm = vm
            )
        }

        // 2. Buscamos el botón que tiene la dificultad (por defecto "Tutorial")
        // Usamos un tag si lo has puesto, o el texto directamente
        val botonDificultad = composeTestRule.onNodeWithText("Tutorial")

        // 3. Simulamos el clic para abrir el desplegable
        botonDificultad.performClick()

        // 4. Seleccionamos "Hard" en el menú que ha aparecido
        composeTestRule.onNodeWithText("Hard").performClick()

        // 5. Verificamos que el botón ahora muestra "Hard"
        composeTestRule.onNodeWithText("Hard").assertIsDisplayed()
    }
}