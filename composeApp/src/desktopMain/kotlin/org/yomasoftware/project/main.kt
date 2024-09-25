package org.yomasoftware.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "MeetingWorkbooksOrganizer",
    ) {
        App()
    }
}