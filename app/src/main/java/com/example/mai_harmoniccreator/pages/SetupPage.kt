package com.example.mai_harmoniccreator.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mai_harmoniccreator.fragments.MainScreenValueInput
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupPage(
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
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MainScreenValueInput(
                    parameters = parameters,
                    onParametersChange = { viewModel.updateHarmonicSignalParameters(it) }
                )
                Button(
                    onClick = { viewModel.onShowSignal() }
                ) { Text("Сгенерировать") }
            }
        }
    }
}