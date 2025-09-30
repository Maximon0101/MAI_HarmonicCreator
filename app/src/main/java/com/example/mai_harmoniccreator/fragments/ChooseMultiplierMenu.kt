package com.example.mai_harmoniccreator.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChooseMultiplierMenu(currentAction: String, onMultiplierChange: (String) -> Unit){
    Box(
        modifier = Modifier,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("$currentAction")
            MinimalDropdownMenu(onMultiplierChange)
        }
    }
}

@Composable
fun MinimalDropdownMenu(onMultiplierChange: (String) -> Unit) {
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
                text = { Text("milli") },
                onClick = {
                    onMultiplierChange("m")
                    expanded = false }
            )
            DropdownMenuItem(
                text = { Text("1") },
                onClick = {
                    onMultiplierChange("1")
                    expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Kilo") },
                onClick = {
                    onMultiplierChange("k")
                    expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Mega") },
                onClick = {
                    onMultiplierChange("M")
                    expanded = false }
            )
        }
    }
}
