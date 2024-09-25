package com.yomasoftware.data.interfaces

import com.example.organizer.db.MonthsTable
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {

    fun getAllWorkbooksFlow (): Flow<List<MonthsTable>>


//    fun dbIsEmpty():Boolean
//    suspend  fun getAllWorkbooks(): List<MonthsTable>
//
//    fun addMonth(newMonth:MonthsTable)
//
//    fun getWeeksOfMonth(queryMonthID:Long): List<AllSimpleWeek>
//
//    fun getMonth(idMonth: Long): MonthsTable
//    fun addWeek(newWeek: WeekWorkbook, monthInfo: MonthsTable)
//    fun getWeekWithAssignaments(idWeek: Long): Flow<List<AllAssignWeekkSchedule>>
//    fun addMonthDownloadDate(downloadDate: MonthsTable)
//
//    fun insertWeek(infoDao: WeekWorkbook, dateWeek: String, idWeek: Long, idMonth: Long)
//    fun insertAssign(assignament: AssignamentsWeekly, idWeek: Long)


}