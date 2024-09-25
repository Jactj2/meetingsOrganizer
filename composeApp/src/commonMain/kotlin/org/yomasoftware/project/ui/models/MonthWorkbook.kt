package org.yomasoftware.project.ui.models

import kotlinx.datetime.LocalDate

data class MonthWorkbook(
    val id : Long,
    val title:String,
    val subtitle:String,
    val date : LocalDate,
    val isDownload : Boolean
)
