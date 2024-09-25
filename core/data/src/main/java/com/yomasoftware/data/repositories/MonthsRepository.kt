package com.yomasoftware.data.repositories

import com.yomasoftware.data.interfaces.LocalDataSource
import com.yomasoftware.data.interfaces.RemoteSource
import kotlinx.coroutines.flow.transform

class MonthsRepository (
    private val localSource: LocalDataSource,
    private val webSource: RemoteSource,
) {

    val dataMonthsDB = localSource.getAllWorkbooksFlow()

}

