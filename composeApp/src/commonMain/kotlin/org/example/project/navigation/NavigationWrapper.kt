package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.screens.GameMenu
import org.example.project.screens.GameScreen
import org.example.project.screens.MainMenu
import org.example.project.screens.Results
import org.example.project.screens.Stats

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(navConfig, Route.MainMenu)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.MainMenu> {
                MainMenu(
                    navigateToGameMenu = { backStack.add(Route.GameMenu) },
                    navigateToStats = { backStack.add(Route.Stats(userId = "user_42")) }
                )
            }
            entry<Route.GameMenu> {
                GameMenu(
                    navigateBack = { backStack.removeLastOrNull() },
                    navigateToGameScreen = { option1, option2 ->
                        backStack.add(Route.GameScreen(selectedOption = option1, selectedOption2 = option2))
                    }
                )
            }
            entry<Route.Stats> { key ->
                Stats(userId = key.userId, navigateBack = { backStack.removeLastOrNull() })
            }
            entry<Route.GameScreen> { routeData ->
                GameScreen(
                    navigateToResults = { backStack.add(Route.Results)},
                    selectedOption = routeData.selectedOption,
                    selectedOption2 = routeData.selectedOption2
                )
            }
            entry<Route.Results> {
                Results(navigateBack = {backStack.add(Route.MainMenu)})
            }
        }
    )
}