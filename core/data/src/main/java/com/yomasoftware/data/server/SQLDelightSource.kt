package com.yomasoftware.data.server

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.example.organizer.db.AllAssignWeekkSchedule
import com.example.organizer.db.AllSimpleWeek
import com.example.organizer.db.AssignsTable
import com.example.organizer.db.MeetingsDB
import com.example.organizer.db.MonthsTable
import com.example.organizer.db.WeeksTable
import com.yomasoftware.data.interfaces.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDate


internal class SQLDelightSource(driver: SqlDriver) : LocalDataSource {


    private val database: MeetingsDB = MeetingsDB(driver)


//    override suspend fun getAllWorkbooks(): List<MonthsTable>  = withContext(Dispatchers.Default){
//        database.monthsTableQueries.readAllUpdate().executeAsList()
//    }

//    override fun addMonth(newMonth: MonthsTable) =
//        database.monthsTableQueries.insertMonthDao(
//            newMonth.copy(
//                idMonthlyMeeting = createMonthlyID(LocalDate.parse(newMonth.dateMonth))
//            )
//        )

//    override fun addWeek(newWeek: WeekDao, monthInfo: MonthsTable) {
//        val dateWeek =
//            MeetingProcesor.getDateDay(newWeek.title, LocalDate.parse(monthInfo.dateMonth))
//        val idWeek = createWeeklyID(dateWeek)
//        addInfoInWeekTable(idWeek,dateWeek.toString(),newWeek,monthInfo.idMonthlyMeeting)
//        newWeek.assignamentsList.forEach {
//            addInfoAssignamentsTable(it,dateWeek,idWeek)
//        }
//    }

//    override fun addMonthDownloadDate(downloadDate: MonthsTable)  =
//        database.monthsTableQueries.updateDownload(downloadDate)

//    override fun insertWeek(infoDao: WeekDao, dateWeek: String, idWeek: Long, idMonth: Long) {
//        try {
//            database.weeksTableQueries.insertWeekShedule(
//                WeeksTable(
//                    idWeek,
//                    dateWeek,
//                    infoDao.title,
//                    infoDao.bibleReading,
//                    infoDao.songsList.toList().toString(),
//                    null,
//                    null,
//                    null,
//                    infoDao.link,
//                    idMonth
//                )
//            )
//        } catch ( e:Exception ) {
//            throw Exception("Error al guardar datos de la semana del : ${infoDao.title}")
//        }
//
//    }

//    override fun insertAssign(assignament: AssignamentsWeekly, idWeek: Long) {
//       try{
//           database.assignsTableQueries.insertAssignament(
//               AssignsTable(
//                   assignament.idAssignament,
//                   assignament.title,
//                   null,
//                   null,
//                   assignament.partNo.toLong(),
//                   assignament.giveIn.toString(),
//                   assignament.meetingPart.toString(),
//                   assignament.type.toString(),
//                   idWeek
//               )
//           )
//       } catch (e:Exception) {
//           throw Exception("Error al registrar la asignacion ${assignament.title} de la fecha ${assignament.date}")
//       }
//    }

//    private fun addInfoInWeekTable(idWeek:Long,dateWeek:String,newWeek: WeekDao,idMonth: Long) =
//        database.weeksTableQueries.insertWeekShedule(
//        WeeksTable(
//            idWeek,
//            dateWeek,
//            newWeek.title.uppercase(),
//            newWeek.bibleReading.uppercase(),
//            newWeek.songsList.toString(),
//            null,
//            null,
//            null,
//            newWeek.link,
//            idMonth
//        )
//    )

//    override fun getWeekWithAssignaments(idWeek: Long): Flow<List<AllAssignWeekkSchedule>> =
//        database.assignsTableQueries.allAssignWeekkSchedule(idWeek).asFlow().mapToList(Dispatchers.IO)

//    private fun addInfoAssignamentsTable(
//        assignamentDao: AssignamentDao,
//        dateWeek: LocalDate,
//        idWeek: Long
//    ) {
//        val partNo = MeetingProcesor.getAssignamentPosition(assignamentDao.title)
//        val giveIn = GiveIn.MAIN_HALL
//        database.assignsTableQueries.insertAssignament(
//            AssignsTable(
//                createAssigmentID(dateWeek,giveIn,partNo),
//                assignamentDao.title,
//                null,
//                null,
//                partNo.toLong(),
//                giveIn.toString(),
//                MeetingPart.entries[assignamentDao.meetingPart-1].toString(),
//                MeetingProcesor.getTypeSpeech( assignamentDao.title).toString(),
//                idWeek
//            )
//        )
//    }

    override fun getAllWorkbooksFlow(): Flow<List<MonthsTable>> =
        database.monthsTableQueries.readAll().asFlow().mapToList(Dispatchers.IO)

//    override fun getMonth(idMonth: Long): MonthsTable  =
//        database.monthsTableQueries.readAll().executeAsList()
//            .first { it.idMonthlyMeeting == idMonth }
//
//    override fun getWeeksOfMonth(queryMonthID: Long): List<AllSimpleWeek> =
//        database.weeksTableQueries.allSimpleWeek(queryMonthID).executeAsList()

    private fun createBaseID(date: LocalDate): Long = ((date.year - 2000) * 1000).toLong()

 //   private fun createMonthlyID(date: LocalDate): Long = createBaseID(date) + date.monthNumber

//    private fun createWeeklyID(dateWeekly: LocalDate): Long =
//        createBaseID(dateWeekly) + dateWeekly.dayOfYear

//    private fun createAssigmentID(dateWeek: LocalDate, giveIn: GiveIn, position: Int) : Long =
//        createBaseID(dateWeek)*10+dateWeek.dayOfMonth*1000+(giveIn.ordinal*100)+position

}

//fun MonthsTable.toEntitieGui(): MonthWorkbook {
//    println("the date download is: $dateDownload")
//   return MonthWorkbook(idMonthlyMeeting, title_meeting, subtitle_meeting!!,LocalDate.parse(dateMonth), !dateDownload.isNullOrBlank())
//}

//fun AllSimpleWeek.toWeeklyWorkbook(): WeeklyWorkbookMeetings =
//    WeeklyWorkbookMeetings(idWeek,LocalDate.parse(date_week),title_meeting,"",
//        "", "","",listOf(), mapOf()
//    )