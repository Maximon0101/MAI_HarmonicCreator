package com.example.mai_harmoniccreator.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.text.isEmpty

@Composable
fun PhaseValueInput(label: String, number: Double, onNumberChange: (Double) -> Unit) {
    val keyboardOption = remember { KeyboardOptions(keyboardType = KeyboardType.Number) }
    var angleUnitText by remember { mutableStateOf("d") }
    var isDegrees by remember { mutableStateOf(true) }

    fun changeAngleUnit(_isDegrees: Boolean, angleText: String) {
        isDegrees = _isDegrees
        angleUnitText = angleText
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = if (isDegrees) {(number).toString()} else {(number / 180).toString()},
            onValueChange = { newText ->
                if (newText.isEmpty()) {
                    onNumberChange(0.0)
                }
                val newNumber = newText.toDoubleOrNull() ?: return@OutlinedTextField // преобразуем строку в число
                if (isDegrees) {onNumberChange(newNumber)} else {onNumberChange(180 * newNumber)}
            },
            label = { Text(label) },
            keyboardOptions = keyboardOption
        )
        ChooseAngleUnitMenu(angleUnitText,
            { isDegrees, angleText -> changeAngleUnit(isDegrees, angleText) }
        )
    }

}