package org.yomasoftware.project.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.yomasoftware.project.data.models.WeeklyWorkbookMeetings
import org.yomasoftware.project.domain.GetAssignamentsWeekCaseUse

class WeeklyViewModel (val idWeek:Long) : ViewModel() {

    init {
        println("ID OF WEEK: $idWeek")
    }
    val weeklyAssigState: StateFlow<WeekAssignamentsState> = GetAssignamentsWeekCaseUse()(idWeek)
        .map { WeekAssignamentsState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = WeekAssignamentsState.Loading,
            started = SharingStarted.WhileSubscribed()
        )

}

sealed interface WeekAssignamentsState {
    data object Loading : WeekAssignamentsState
    data class Success(val week: WeeklyWorkbookMeetings) : WeekAssignamentsState
}