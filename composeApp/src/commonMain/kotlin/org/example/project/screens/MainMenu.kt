package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.Napier
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainMenu(navigateTo2: () -> Unit, navigateTo3: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Memory Game", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(20.dp))
        Button(
            onClick = navigateTo2,
            modifier = Modifier.width(150.dp)
        ) {
            Text("Play", style = MaterialTheme.typography.headlineMedium) //lleva a elegir baraja y tipo de juego
        }

        Spacer(Modifier.height(8.dp))
        Button(
            onClick = navigateTo3,
            modifier = Modifier.width(150.dp)
        ) {
            Text("Stats", style = MaterialTheme.typography.headlineMedium) //maximo score y el minimo timepo en acabar una partida
        }



//        Spacer(Modifier.height(8.dp))
//        Image(painterResource(Res.drawable.compose_multiplatform), contentDescription = "Argo")
//
//        Spacer(Modifier.height(8.dp))
//        Napier.d("Missatge debug")
//        Napier.i("Missatge info")
//        Napier.w("Missatge warning")
//        Napier.e("Missatge error")
    }
}
