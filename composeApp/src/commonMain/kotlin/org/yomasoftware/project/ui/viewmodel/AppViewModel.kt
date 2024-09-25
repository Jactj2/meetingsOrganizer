package org.yomasoftware.project.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yomasoftware.domain.usesCases.GetWorkbooksMonths


import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import org.yomasoftware.project.domain.utils.toListUI

import org.yomasoftware.project.ui.models.MonthWorkbook







class AppViewModel : ViewModel() {


//    val monthsUiState: StateFlow<MonthsUiState> = GetWorkbooksMonths()()
//        .transform {
//            when(it.isEmpty()){
//                true -> emit(MonthsUiState.Loading)
//                false -> emit(MonthsUiState.Success(it.toListUI()))
//            }
//        }
//        .stateIn(
//            scope = viewModelScope,
//            initialValue = MonthsUiState.Loading,
//            started = SharingStarted.WhileSubscribed()
//        )

//    fun checkUpdateWorkbooks() = DownloadAllWorkbooksWeb()()
//
//
//    fun downloadMonthInfo(titleMonth:String,linkSource:String) : Flow<Float> =
//        DownloadProgressMonthInfoCasueUse()(titleMonth,linkSource)

}

sealed interface MonthsUiState {
    data object Loading : MonthsUiState
    data class Success(val sourceList: List<MonthWorkbook>) : MonthsUiState
}

//
//sealed interface WeeksListUiState {
//    data object Loading : WeeksListUiState
//    data class Success(val sourceList: List<WeekDao>) : WeeksListUiState
//
//}
//
//data class ViewOfWeeksState(
//    val isLoading: Boolean,
//    val sourceList: List<WeeklyWorkbookMeetings>,
//)



