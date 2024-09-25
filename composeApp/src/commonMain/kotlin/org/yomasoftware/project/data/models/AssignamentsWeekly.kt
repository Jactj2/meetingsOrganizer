package org.yomasoftware.project.data.models

import kotlinx.datetime.LocalDate
import sqldelight.meetingsdb.data.GiveIn
import sqldelight.meetingsdb.data.MeetingPart
import sqldelight.meetingsdb.data.TypePart

data class AssignamentsWeekly (
    val idAssignament : Long,
    val title:String ="",
    val name : String,
    val asistant: String,
    val partNo: Int,
    val giveIn : GiveIn,
    val meetingPart : MeetingPart,
    val type : TypePart,
    val date : LocalDate,
)