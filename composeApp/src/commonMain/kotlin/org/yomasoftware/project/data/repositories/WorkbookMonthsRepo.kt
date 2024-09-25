package org.yomasoftware.project.data.repositories


//import com.example.organizer.db.MonthsTable
//import kotlinx.coroutines.flow.transform
//import kotlinx.datetime.LocalDate
//import org.yomasoftware.project.data.models.web.MonthDao
//import org.yomasoftware.project.domain.models.MonthWorkbook
//
//
//class WorkbookMonthsRepo (
//    private val localSource: LocalDataSource,
//    private val webSource: WebDataSource,
//) {
//
//    val dataMonthsDB = localSource.getAllWorkbooksFlow().transform {
//        emit(it.map {data -> data.toDomain() }.toList())
//    }
//
//    suspend fun downloadMonthsInfo() : List<MonthDao>{
//        val remoteLinks = webSource.getUpdateWoorkbooks()
//        val localLinks = localSource.getAllWorkbooks()
//        var updateLinks = remoteLinks
//
//        localLinks.forEach { local ->
//            updateLinks = remoteLinks.filter { it.linkPage == local.link_meeting }
//        }
//
//        return  updateLinks
//    }
//
//    fun addMonth(newData: MonthWorkbook) = localSource.addMonth(newData.toTable())
//
//}
//
//internal fun MonthWorkbook.toTable(): MonthsTable =
//    MonthsTable(
//        idMonthlyMeeting = id,
//        title_meeting = title,
//        subtitle_meeting = subtitle,
//        link_meeting = linkPage,
//        dateMonth = date.toString(),
//        dateDownload = null
//        )
//
//internal fun MonthsTable.toDomain() : MonthWorkbook =
//    MonthWorkbook(
//        id = idMonthlyMeeting,
//        title = title_meeting,
//        subtitle = subtitle_meeting!!,
//        linkPage = link_meeting,
//        date = LocalDate.parse(dateMonth),
//        listWeeklyMeetings = listOf(),
//        isDownload = !dateDownload.isNullOrEmpty(),
//        )
