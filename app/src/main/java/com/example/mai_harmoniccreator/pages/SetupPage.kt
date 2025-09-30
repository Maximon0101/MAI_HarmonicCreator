package com.example.mai_harmoniccreator.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mai_harmoniccreator.data.HarmonicSignalParameters
import com.example.mai_harmoniccreator.fragments.MainScreenValueInput
import com.example.mai_harmoniccreator.navigation.DrawDestination
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupPage(
    navController: NavHostController,
    viewModel: HarmonicViewModel
) {
    val parameters by viewModel.harmonicSignalParametersFlow.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Настройка сигнала") })
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MainScreenValueInput(
                    parameters = parameters,
                    onParametersChange = { viewModel.updateHarmonicSignalParameters(it) }
                )
                Button(
                    onClick = {navController.navigate(route = DrawDestination)}
                ) { Text("Сгенерировать") }
            }
        }
    }
}