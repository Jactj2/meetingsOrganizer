package com.yomasoftware.data.models


data class MonthDao(
    val title: String,
    val linkPage: String,
    val listWeeklyMeetings: List<WeekDao>,
)

data class WeekDao(
    val title: String,
    val link: String,
    val bibleReading: String,
    val songsList: Set<String>,
    val assignamentsList: List<AssignmentDao>,
)

internal data class AssignmentDao(
    val title: String,
    val meetingPart: Int,
    val comment: String,
)