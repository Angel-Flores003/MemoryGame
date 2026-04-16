package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.Napier
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun Pantalla1Screen(navigateTo2: () -> Unit, navigateTo3: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Pantalla 1", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(24.dp))
        Button(onClick = navigateTo2) { Text("Ir a Pantalla2") }

        Spacer(Modifier.height(8.dp))
        Button(onClick = navigateTo3) { Text("Ir a Pantalla3") }

        Spacer(Modifier.height(8.dp))
        Image(painterResource(Res.drawable.compose_multiplatform), contentDescription = "Argo")

        Spacer(Modifier.height(8.dp))
        Napier.d("Missatge debug")
        Napier.i("Missatge info")
        Napier.w("Missatge warning")
        Napier.e("Missatge error")
    }
}
