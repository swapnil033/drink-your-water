package com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.AddDailyProgressEvent
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.EditTextState
import com.swapnil.drinkyourwater.feature_dailyProgress.presentation.addDailyProgressScreen.WaterUnits

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDropDown(
    editTextState: EditTextState,
    onValueChange: (String) -> Unit,
    onUnitChange: (WaterUnits) -> Unit,
    modifier: Modifier = Modifier
) {

    TextField(
        value = editTextState.text,
        onValueChange = onValueChange,
        label = {
            Text(text = "Target (in ${editTextState.unit.unit})")
        },
        trailingIcon = {
            DropDownText(
                selectedUnit = editTextState.unit,
                onUnitChange = onUnitChange
            )
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}