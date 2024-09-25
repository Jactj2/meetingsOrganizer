package org.yomasoftware.project.data.models

import com.example.organizer.db.AllAssignWeekkSchedule
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import sqldelight.meetingsdb.data.GiveIn
import sqldelight.meetingsdb.data.MeetingPart
import sqldelight.meetingsdb.data.TypePart




fun String.toList():List<String> =
    this.replace("[",""
    ).replace("]","")
        .split(",").map{it.trim()}

fun AllAssignWeekkSchedule.toAssignamentsGUI():AssignamentsWeekly =
    AssignamentsWeekly(
        idAssign,
        title,
        name ?: "",
        assistant ?: "",
        part_no.toInt(),
        GiveIn.valueOf(give_in),
        MeetingPart.valueOf(meeting_part),
        TypePart.valueOf(assignament_type),
        LocalDate.parse(date_week),
    )

fun LocalDateTime.Companion.now() : LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDate.Companion.now() : LocalDate {
    return LocalDateTime.now().date
}
fun LocalTime.Companion.now() : LocalTime {
    return LocalDateTime.now().time
}