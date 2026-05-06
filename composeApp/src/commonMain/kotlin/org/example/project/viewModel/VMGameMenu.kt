package org.example.project.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class VMGameMenu : ViewModel() {
    val playerOptions = listOf("VS 1", "1 VS 1", "VS 3", "2 VS 2", "VS 4")
    val difficultyOptions = listOf("Tutorial", "Easy", "Medium", "Hard", "Insane")

    // Estados de selección
    var selectedPlayers by mutableStateOf(playerOptions[0])
        private set

    var selectedDifficulty by mutableStateOf(difficultyOptions[0])
        private set

    // Funciones para cambiar la selección
    fun changePlayers(newOption: String) {
        selectedPlayers = newOption
    }

    fun changeDifficulty(newOption: String) {
        selectedDifficulty = newOption
    }
}