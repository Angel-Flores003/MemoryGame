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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.example.project.navigation.Route
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameScreen(
    navigateToResults: () -> Unit,
    selectedOption: String,
    selectedOption2: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var score by remember { mutableStateOf(false) }

        when (selectedOption2) {
            "Tutorial" -> GameGrid(rows = 2, cols = 2)
            "Easy"     -> GameGrid(rows = 4, cols = 2)
            "Medium"   -> GameGrid(rows = 4, cols = 4)
            "Hard"     -> GameGrid(8, 4)
            "Insane"   -> GameGrid(8, 8)
        }

        Button(onClick = { navigateToResults() },
            modifier = Modifier.width(150.dp)) {
            Text(
                "Go Results",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/*@Composable
fun GameGrid(rows: Int, cols: Int) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        repeat(rows) {
            Row(
                modifier = Modifier
                    .weight(1f) // Esto hace que cada fila mida lo mismo de alto
                    .fillMaxWidth()
            ) {
                repeat(cols) {
                    // Cada carta debe tener weight(1f) para repartirse el ancho
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp) // Espaciado entre cartas
                    ) {
                        CartaCard()
                    }
                }
            }
        }
    }
}*/

@Composable
fun GameGrid(rows: Int, cols: Int) {
    Column {
        repeat(rows) {
            Row {
                repeat(cols) {
                    CartaCard()
                }
            }
        }
    }
}