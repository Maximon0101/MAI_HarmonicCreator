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

@Composable
fun ValueInput(label: String, number: Double, onNumberChange: (Double) -> Unit) {
    val keyboardOption = remember { KeyboardOptions(keyboardType = KeyboardType.Number) }
    var multiplierStateText by remember { mutableStateOf("1") }
    var multiplierState by remember { mutableStateOf(1.0) }

    fun changeMultiplierState(state: Double, stateText: String) {
        multiplierState = state
        multiplierStateText = stateText
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = (number / multiplierState).toString(),
            onValueChange = { newText ->
                if (newText.isEmpty()) {
                    onNumberChange(0.0)
                }
                val newNumber = newText.toDoubleOrNull() ?: return@OutlinedTextField // преобразуем строку в число
                onNumberChange(newNumber * multiplierState)
            },
            label = { Text(label) },
            keyboardOptions = keyboardOption
        )
        ChooseMultiplierMenu(multiplierStateText,
            { state, stateText -> changeMultiplierState(state, stateText) }
        )
    }

}