package com.example.mai_harmoniccreator.pages

import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.mai_harmoniccreator.navigation.SetupDestination
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawPage(
    navController: NavHostController,
    viewModel: HarmonicViewModel
) {
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
            TextButton (
                onClick = {
                    navController.navigate(route = SetupDestination)
                }
            ) { Text("Вернуться") }
        }
    }
}