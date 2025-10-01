package com.example.mai_harmoniccreator.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mai_harmoniccreator.fragments.Graph
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DrawPage(
    viewModel: HarmonicViewModel
) {
    val graphData by viewModel.graphDataFlow.collectAsState()
    val spectrumData by viewModel.spectrumDataFlow.collectAsState()

    Scaffold (
        modifier = Modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Сигнал") })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp), // Добавим общие отступы для контента
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Расстояние между графиками
                verticalAlignment = Alignment.CenterVertically
            ) {
                val graphModifier = Modifier
                    .weight(1f) // Занять половину ширины
                    .fillMaxHeight() // Занять всю высоту, предоставленную Row
                //Левый график - сигнал
                if (graphData == null) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        LoadingIndicator()
                    }
                } else {
                    Graph(data = graphData!!, modifier = graphModifier)
                }

                //Правый график - спектр
                if (spectrumData == null) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        LoadingIndicator()
                    }
                } else {
                    Graph(data = spectrumData!!, modifier = graphModifier)
                }
            }

            TextButton(
                onClick = { viewModel.onBackToSetup() }
            ) {
                Text("Вернуться")
            }
        }
    }
}