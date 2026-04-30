package org.example.project.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.example.project.screens.GameGrid

class MainViewModel() : ViewModel() {
    var score by mutableStateOf(false)

    //var score by remember { mutableStateOf(false) }


    @Composable
    fun ScreenGameByDificulty(selectedOption2: String) {


        when (selectedOption2) {
            "Tutorial" -> GameGrid(2, 2)
            "Easy" -> GameGrid(4, 2)
            "Medium" -> GameGrid(4, 4)
            "Hard" -> GameGrid(8, 4)
            "Insane" -> GameGrid(8, 8)
        }
    }
}