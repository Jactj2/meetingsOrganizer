package org.yomasoftware.project.domain.utils

import org.yomasoftware.project.domain.models.MonthWorkbook as MonthDomain
import org.yomasoftware.project.ui.models.MonthWorkbook as MonthUI



fun List<MonthDomain>.toListUI() : List<MonthUI> =
    this.map { it.toUI() }.toList()

fun  MonthDomain.toUI() : MonthUI =
    MonthUI( id , title , subtitle , date , isDownload )

//fun MonthDomain.Companion.fromWeb(monthDao: MonthDao, date:LocalDate): MonthDomain =
//    MonthDomain(
//        0L,
//        title = monthDao.title,
//        subtitle = monthDao.title,
//        linkPage = monthDao.linkPage,
//        listWeeklyMeetings = monthDao.listWeeklyMeetings.toListDomain(),
//        date = date,
//        )
//
//private fun List<WeekDao>.toListDomain(): List<WeekDomain> =
//    this.map { it.toDomain() }.toList()
//
//private fun WeekDao.toDomain() : WeekDomain =
//    WeekDomain(title, link, bibleReading, songsList, assignamentsList)
