package com.example.mai_harmoniccreator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mai_harmoniccreator.fragments.MainScreenValueInput
import com.example.mai_harmoniccreator.navigation.ApplicationNavigationGraph
import com.example.mai_harmoniccreator.ui.theme.MAI_HarmonicCreatorTheme
import com.example.mai_harmoniccreator.utils.AndroidOrientation
import com.example.mai_harmoniccreator.utils.LocalAndroidOrientation
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val orientationManager = AndroidOrientation(
            orientation = requestedOrientation,
            activity = this
        )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalAndroidOrientation provides orientationManager
            ) {
                MAI_HarmonicCreatorTheme {
                    val navController = rememberNavController()
                    val harmonicViewModel = viewModel<HarmonicViewModel> { HarmonicViewModel(navController) }
                    ApplicationNavigationGraph(
                        viewModel = harmonicViewModel,
                        navController = navController,
                    )
                }
            }
        }
    }
}

