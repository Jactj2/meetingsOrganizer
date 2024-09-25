package org.yomasoftware.project.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.organizer.db.MeetingsDB

actual class DriverFactory (private val context : Context){
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(MeetingsDB.Schema,context,"Meetings.db")
}