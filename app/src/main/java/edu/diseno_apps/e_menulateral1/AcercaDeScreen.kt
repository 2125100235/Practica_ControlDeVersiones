package edu.diseno_apps.e_menulateral1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AcercaDeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Acerca de",
            fontSize = 20.sp
        )
        HorizontalDivider()
        Text(text = "Desarrollado por Carlos Daniel Vargas Pérez. TSU DSM 4-B T/M")
        Text(text = "Version 1.0.0")
        Text(text = "Ejemplo de navegación por medio de un menú lateral")
    }
}
