package com.yomasoftware.domain.usesCases


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.yomasoftware.project.domain.models.MonthWorkbook
import com.yomasoftware.data.repositories.MonthsRepository
import com.yomasoftware.domain.utils.toListDomain
import kotlinx.coroutines.flow.transform

class GetWorkbooksMonths : KoinComponent {

//    private val repositoryDB = MonthsRepository()
//
//    operator fun invoke(): Flow<List<MonthWorkbook>> =
//        repositoryDB.dataMonthsDB
//            .flowOn(Dispatchers.IO + CoroutineName("dbFlow"))
//            .transform { emit(it.toListDomain())  }

}

