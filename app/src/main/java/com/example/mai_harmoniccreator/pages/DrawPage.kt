package com.example.mai_harmoniccreator.pages

import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.graphics.shapes.RoundedPolygon
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.mai_harmoniccreator.fragments.Graph
import com.example.mai_harmoniccreator.navigation.SetupDestination
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DrawPage(
    viewModel: HarmonicViewModel
) {
    val graphData by viewModel.graphDataFlow.collectAsState()

    Scaffold (
        modifier = Modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Сигнал") })
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (graphData == null) {LoadingIndicator()} else {
                    Graph(graphData!!)
                }
                TextButton (
                    onClick = { viewModel.onBackToSetup() }
                ) { Text("Вернуться") }
            }
        }
    }
}