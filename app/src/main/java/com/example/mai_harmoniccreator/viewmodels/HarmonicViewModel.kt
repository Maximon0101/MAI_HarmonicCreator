package com.example.mai_harmoniccreator.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mai_harmoniccreator.data.HarmonicSignalParameters
import com.example.mai_harmoniccreator.data.physicalUnits.deg
import com.example.mai_harmoniccreator.data.physicalUnits.hz
import com.example.mai_harmoniccreator.data.physicalUnits.rad
import com.example.mai_harmoniccreator.data.physicalUnits.v
import kotlinx.coroutines.flow.MutableStateFlow

class HarmonicViewModel : ViewModel() {
    private val _harmonicSignalParametersFlow: MutableStateFlow<HarmonicSignalParameters> = MutableStateFlow(
        HarmonicSignalParameters(
            amplitude = 0.v,
            frequency = 0.hz,
            phase = 0.deg
        )
    )

    val harmonicSignalParametersFlow = _harmonicSignalParametersFlow

    fun updateAmplitude(newAmplitude: Double) {
        val newHarmonicSignalParameters = _harmonicSignalParametersFlow.value.copy(
            amplitude = newAmplitude.v
        )
        _harmonicSignalParametersFlow.value = newHarmonicSignalParameters
    }

    fun updateFrequency(newFrequency: Double) {
        val newHarmonicSignalParameters = _harmonicSignalParametersFlow.value.copy(
            frequency = newFrequency.hz
        )
        _harmonicSignalParametersFlow.value = newHarmonicSignalParameters
    }

    fun updatePhase(newPhase: Double, isDegrees: Boolean) {
        val newHarmonicSignalParameters = _harmonicSignalParametersFlow.value.copy(
            phase = if (isDegrees) {newPhase.deg} else {newPhase.rad}
        )
        _harmonicSignalParametersFlow.value = newHarmonicSignalParameters
    }

    fun updateHarmonicSignalParameters(newParameters: HarmonicSignalParameters) {
        _harmonicSignalParametersFlow.value = newParameters
    }
}