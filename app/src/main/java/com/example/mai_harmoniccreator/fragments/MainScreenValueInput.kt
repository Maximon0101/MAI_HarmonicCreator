package com.example.mai_harmoniccreator.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.mai_harmoniccreator.data.HarmonicSignalParameters
import com.example.mai_harmoniccreator.data.physicalUnits.deg
import com.example.mai_harmoniccreator.data.physicalUnits.hz
import com.example.mai_harmoniccreator.data.physicalUnits.v

@Composable
fun MainScreenValueInput(parameters: HarmonicSignalParameters, onParametersChange: (HarmonicSignalParameters) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ValueInput(
            "Амплитуда, Вольты",
            parameters.amplitude.value,
            { onParametersChange(parameters.copy(amplitude = it.v)) }
        )
        ValueInput(
            "Частота, Герцы",
            parameters.frequency.value,
            { onParametersChange(parameters.copy(frequency = it.hz)) }
        )
        ValueInput(
            "Фаза",
            parameters.phase.inDegrees,
            { onParametersChange(parameters.copy(phase = it.deg)) }
        )
    }
}