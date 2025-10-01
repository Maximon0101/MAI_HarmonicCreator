package com.example.mai_harmoniccreator.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ChooseAngleUnitMenu(currentAction: String, onMultiplierChange: (Boolean, String) -> Unit){
    Box(
        modifier = Modifier,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("$currentAction")
            MinimalDropdownMenuAngle(onMultiplierChange)
        }
    }
}

@Composable
fun MinimalDropdownMenuAngle(onMultiplierChange: (Boolean, String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier,
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("градусы") },
                onClick = {
                    onMultiplierChange(true, "d")
                    expanded = false }
            )
            DropdownMenuItem(
                text = { Text("радианы (n of PI)") },
                onClick = {
                    onMultiplierChange(false, "r")
                    expanded = false }
            )
        }
    }
}
