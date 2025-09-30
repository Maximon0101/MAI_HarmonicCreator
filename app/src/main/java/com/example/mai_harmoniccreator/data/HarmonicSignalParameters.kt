package com.example.mai_harmoniccreator.data

import com.example.mai_harmoniccreator.data.physicalUnits.Angle
import com.example.mai_harmoniccreator.data.physicalUnits.Hertz
import com.example.mai_harmoniccreator.data.physicalUnits.Volt

data class HarmonicSignalParameters(
    val amplitude: Volt,
    val frequency: Hertz,
    val phase: Angle,
)
