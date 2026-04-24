package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {
    @Serializable
    data object MainMenu : Route()
    @Serializable
    data object GameMenu : Route()
    @Serializable
    data class Stats(val userId: String) : Route()
    @Serializable
    data class GameScreen(val selectedOption: String, val selectedOption2: String) : Route()
    @Serializable
    data object Results : Route()
}