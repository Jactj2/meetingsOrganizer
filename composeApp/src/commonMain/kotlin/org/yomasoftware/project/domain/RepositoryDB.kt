package org.yomasoftware.project.domain

import com.example.organizer.db.AllAssignWeekkSchedule
import com.example.organizer.db.AllSimpleWeek
import com.example.organizer.db.MonthsTable
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import org.yomasoftware.project.data.models.AssignamentsWeekly
import org.yomasoftware.project.data.models.web.WeekDao

interface RepositoryDB {
    //suspend fun downloadMonthsInfo()
    suspend fun downloadWeekInfo(linkWeek: String):WeekDao
    suspend fun getWeekLinksOfMonth(link: String): List<String>
    fun getAllMonths(): Flow<List<MonthsTable>>
    suspend fun getWeeksOfMonth(idMonth: Long): List<AllSimpleWeek>
    fun getWeekAssigns(idWeek:Long) : Flow<List<AllAssignWeekkSchedule>>
    fun addWeeksOfWeb(dataWeekOfMonth: List<WeekDao>, idMonth: Long)
    fun queryDateOfMonth(monthId: Long): String
    fun insertRegisterWeek(
        weekDao: WeekDao,
        dateWorkbookWeek: LocalDate,
        dateWorkbookMonth: LocalDate
    ): Long

    fun insertRegisterAssign(assignInfo: AssignamentsWeekly, idWeek: Long)

}