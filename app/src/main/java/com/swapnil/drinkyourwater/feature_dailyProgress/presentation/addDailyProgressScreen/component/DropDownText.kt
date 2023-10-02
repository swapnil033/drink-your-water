package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.WaterUnits

@Composable
fun DropDownText(
    selectedUnit: WaterUnits,
    modifier: Modifier = Modifier,
    onUnitChange: (WaterUnits) -> Unit,
) {

    var showMenu by remember { mutableStateOf(false) }

    Box(modifier = modifier){
        Row {
            Text(
                text = if (selectedUnit == WaterUnits.Litter) "l." else "ml.",
                modifier = Modifier.clickable {
                    showMenu = !showMenu
                }
            )
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }) {
            DropdownMenuItem(
                text = { Text(text = "l.") },
                onClick = {
                    onUnitChange(WaterUnits.Litter)
                    showMenu = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "ml.") },
                onClick = {
                    onUnitChange(WaterUnits.MilliLiter)
                    showMenu = false
                }
            )
        }

    }

}