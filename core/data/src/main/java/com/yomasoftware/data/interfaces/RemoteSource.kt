package com.yomasoftware.data.interfaces

import com.yomasoftware.data.models.MonthDao
import com.yomasoftware.data.models.WeekDao


interface RemoteSource {
    suspend fun getUpdateWoorkbooks(): List<MonthDao>
    suspend fun getInfoMonthWorkbook(monthLink: String): List<WeekDao>
    suspend fun getLinksWeeksOfMonth(link: String): List<String>
    suspend fun donwloadWorkbookWeek(linkWeek: String): WeekDao
}