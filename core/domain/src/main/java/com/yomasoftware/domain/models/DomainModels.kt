package org.yomasoftware.project.domain.models

import kotlinx.datetime.LocalDate

data class MonthWorkbook(
    val id: Long = 0L,
    val title: String,
    val subtitle: String,
    val linkPage: String,
    val listWeeklyMeetings: List<WeekWorkbook>,
    val date: LocalDate,
    val isDownload: Boolean,
) {
    companion object
}

data class WeekWorkbook(
    val title: String,
    val link: String,
    val bibleReading: String,
    val songsList: Set<String>,
    val assignmentsList: List<Assignment>,
)

data class Assignment(val title: String)