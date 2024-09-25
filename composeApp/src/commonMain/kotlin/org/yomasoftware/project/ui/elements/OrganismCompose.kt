package org.yomasoftware.project.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextAssigned(
    assignName: String,
) {
    var text by rememberSaveable { mutableStateOf(assignName) }

    OutlinedTextField(
        modifier = Modifier.padding(start = 16.dp),
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(
                "Sin asignar",
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Light,
            )
        },
        singleLine = true

    )
}
