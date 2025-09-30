package com.example.mai_harmoniccreator.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mai_harmoniccreator.data.HarmonicSignalParameters
import com.example.mai_harmoniccreator.fragments.MainScreenValueInput
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel

@Composable
fun SetupPage(
    viewModel: HarmonicViewModel
) {
    val parameters by viewModel.harmonicSignalParametersFlow.collectAsState()
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            MainScreenValueInput(
                parameters = parameters,
                onParametersChange = { viewModel.updateHarmonicSignalParameters(it) }
            )
            Button(
                onClick = {}
            ) { Text("aboba") }
        }
    }
}