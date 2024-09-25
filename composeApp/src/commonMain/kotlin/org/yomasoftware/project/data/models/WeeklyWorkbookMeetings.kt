package org.yomasoftware.project.data.models

import kotlinx.datetime.LocalDate
import sqldelight.meetingsdb.data.MeetingPart

data class WeeklyWorkbookMeetings(
    val id:Long,
    val date: LocalDate,
    val title:String,
    val weeklyReading: String,
    val meetingPresident: String,
    val initial_prayer: String,
    val final_prayer: String,
    val songs: List<String>,
    val assignamentList: Map<MeetingPart, List<AssignamentsWeekly>>
)
