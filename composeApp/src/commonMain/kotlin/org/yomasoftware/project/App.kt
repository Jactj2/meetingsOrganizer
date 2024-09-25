package org.yomasoftware.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import org.jetbrains.compose.ui.tooling.preview.Preview
import org.yomasoftware.project.ui.NavigatorApp

@Composable
@Preview
fun App() {

    val isFirst= rememberSaveable { mutableStateOf(true) }

    MaterialTheme {
        NavigatorApp(
            isFirst = isFirst.value,
            firstOnChange = {
                isFirst.value = false
                println("FIRST IS: $isFirst")
            }
        )
    }
}

