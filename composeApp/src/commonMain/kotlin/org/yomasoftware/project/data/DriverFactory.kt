package org.yomasoftware.project.data

import app.cash.sqldelight.db.SqlDriver


expect class DriverFactory {
    fun createDriver (): SqlDriver
}