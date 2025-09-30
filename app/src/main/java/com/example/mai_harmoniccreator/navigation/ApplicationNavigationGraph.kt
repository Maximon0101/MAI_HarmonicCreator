package com.example.mai_harmoniccreator.navigation

import android.content.pm.ActivityInfo
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mai_harmoniccreator.navigation.utils.navTypeOf
import com.example.mai_harmoniccreator.pages.DrawPage
import com.example.mai_harmoniccreator.pages.SetupPage
import com.example.mai_harmoniccreator.utils.LocalAndroidOrientation
import com.example.mai_harmoniccreator.viewmodels.HarmonicViewModel
import kotlin.reflect.typeOf

@Composable
fun ApplicationNavigationGraph (
    viewModel: HarmonicViewModel,
    navController: NavHostController,
    ) {
    val orientationConfiguration = LocalAndroidOrientation.current

    NavHost(
        navController = navController,
        startDestination = SetupDestination,
    ) {
        composable<SetupDestination>(
            enterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            typeMap = mapOf(
                typeOf<Double>() to navTypeOf<Double>(),
                typeOf<Float>() to navTypeOf<Float>(),
            )
        ) {
            orientationConfiguration.setOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT)
            SetupPage(navController, viewModel)
        }
        composable<DrawDestination>(
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
            typeMap = mapOf(
                typeOf<Double>() to navTypeOf<Double>(),
                typeOf<Float>() to navTypeOf<Float>(),
            )
        ) {
            orientationConfiguration.setOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
            DrawPage(navController, viewModel)
        }
    }
}