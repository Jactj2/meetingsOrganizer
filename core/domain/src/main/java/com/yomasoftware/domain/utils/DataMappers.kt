package com.yomasoftware.domain.utils

import kotlinx.datetime.LocalDate
import org.yomasoftware.project.domain.models.MonthWorkbook as MonthsDomain
import com.example.organizer.db.MonthsTable as MonthsDB


internal fun List<MonthsDB>.toListDomain(): List<MonthsDomain>  =
    this.map { it.toDomain() }.toList()

internal fun MonthsDB.toDomain() : MonthsDomain =
    MonthsDomain(
        id = idMonthlyMeeting,
        title = title_meeting,
        subtitle = subtitle_meeting!!,
        linkPage = link_meeting,
        date = LocalDate.parse(dateMonth),
        listWeeklyMeetings = listOf(),
        isDownload = !dateDownload.isNullOrEmpty(),
    )