package org.example.project.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Results(navigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val Colorprincipal = Color(0xFF33A17D)
        val ColorBrillo = Color(0xFF1EC991)//0xFFFFCCCC)

        val transition = rememberInfiniteTransition(label = "shimmer")
        val xOffset by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "desplazamiento"
        )
        // Degradado
        val brush = Brush.linearGradient(
            colors = listOf(
                Colorprincipal,
                ColorBrillo,
                Colorprincipal
            ),
            start = Offset(xOffset - 200f, 0f),
            end = Offset(xOffset, 0f)
        )
        Text(
            text = "You Win",

            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                brush = brush,
                fontWeight = FontWeight.Black,
                fontSize = 40.sp
            )
        )
        Button(onClick = { navigateBack() },
            modifier = Modifier.width(150.dp)) {
            Text(
                "Go Back",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

