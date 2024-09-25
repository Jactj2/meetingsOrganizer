package org.yomasoftware.project.data.repositories

import com.example.organizer.db.AllAssignWeekkSchedule
import com.example.organizer.db.AllSimpleWeek
import com.example.organizer.db.MonthsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import org.yomasoftware.project.data.models.AssignamentsWeekly
import org.yomasoftware.project.data.models.now
import org.yomasoftware.project.data.models.web.WeekDao
import org.yomasoftware.project.domain.RepositoryDB
import sqldelight.meetingsdb.data.GiveIn


class RepositoryDBImp(
    private val localSource: LocalDataSource,
    private val webSource: WebDataSource,
) : RepositoryDB {

//    suspend fun downloadMonthsInfo() {
//        val remoteLinks = webSource.getUpdateWoorkbooks()
//        val localLinks = localSource.getAllWorkbooks()
//        var updateLinks = remoteLinks
//
//        localLinks.forEach { local ->
//            updateLinks = remoteLinks.filter { it.linkPage == local.link_meeting }
//        }
//        updateLinks.let { source -> source.forEach { localSource.addMonth(it) } }
//    }

    override suspend fun downloadWeekInfo(linkWeek: String): WeekDao =
        webSource.donwloadWorkbookWeek(linkWeek)

    override fun getAllMonths(): Flow<List<MonthsTable>> = localSource.getAllWorkbooksFlow()

    override suspend fun getWeeksOfMonth(idMonth: Long): List<AllSimpleWeek> {
        while (localSource.getWeeksOfMonth(idMonth).isEmpty()) {
            updateWeeksOfMonth(idMonth)
        }
        return localSource.getWeeksOfMonth(idMonth)
    }

    override fun getWeekAssigns(idWeek: Long): Flow<List<AllAssignWeekkSchedule>> {
        return localSource.getWeekWithAssignaments(idWeek)
    }

    override suspend fun getWeekLinksOfMonth(link: String): List<String> =
        withContext(Dispatchers.IO) { webSource.getLinksWeeksOfMonth(link) }

    override fun addWeeksOfWeb(dataWeekOfMonth: List<WeekDao>, idMonth: Long) {
TODO()
//        try {
//            val monthInfo = localSource.getMonth(idMonth)
//            dataWeekOfMonth.forEach {
//                localSource.addWeek(it, monthInfo)
//            }
//            updateMonthInfo(monthInfo)
//        } catch (e: Exception) {
//            throw Exception("Formato de pagina no reconocido")
//        }

    }

    override fun queryDateOfMonth(monthId: Long): String = localSource.getMonth(monthId).dateMonth

    override fun insertRegisterWeek(
        weekDao: WeekDao,
        dateWorkbookWeek: LocalDate,
        dateWorkbookMonth: LocalDate
    ): Long {
        val idWeek = createWeeklyID(dateWorkbookWeek)
        val idMonth = createMonthlyID(dateWorkbookWeek)
        localSource.insertWeek(weekDao,dateWorkbookWeek.toString(),idWeek,idMonth)
        return idWeek
    }

    override fun insertRegisterAssign(assignInfo: AssignamentsWeekly, idWeek: Long) {
        val assignament = assignInfo.copy(
            idAssignament = createAssigmentID(
                assignInfo.date,
                assignInfo.giveIn,
                assignInfo.partNo
            ))
        localSource.insertAssign(assignament,idWeek)
    }

    private fun updateMonthInfo(monthsTable: MonthsTable) {
        val dateToday = LocalDate.now().toString()
        val newMonth = monthsTable.copy(dateDownload = dateToday)
        localSource.addMonthDownloadDate(newMonth)
    }

    private suspend fun updateWeeksOfMonth(idMonth: Long) {
        val monthInfo = localSource.getMonth(idMonth)

        webSource.getInfoMonthWorkbook(monthInfo.link_meeting).forEach {
            println("Agregando ${it.title}")
            localSource.addWeek(it, monthInfo)
        }

    }


    private fun createBaseID(date: LocalDate): Long = ((date.year - 2000) * 1000).toLong()

    private fun createMonthlyID(date: LocalDate): Long = createBaseID(date) + date.monthNumber

    private fun createWeeklyID(dateWeekly: LocalDate): Long =
        createBaseID(dateWeekly) + dateWeekly.dayOfYear

    private fun createAssigmentID(dateWeek: LocalDate, giveIn: GiveIn, position: Int) : Long =
        createBaseID(dateWeek)*10+dateWeek.dayOfMonth*1000+(giveIn.ordinal*100)+position
}
