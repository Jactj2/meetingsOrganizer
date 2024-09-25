package org.yomasoftware.project.di


import org.yomasoftware.project.data.repositories.RepositoryDBImp
import org.yomasoftware.project.data.repositories.WorkbookMonthsRepo
import org.yomasoftware.project.domain.RepositoryDB
import org.yomasoftware.project.ui.viewmodel.AppViewModel
import org.yomasoftware.project.ui.viewmodel.WeeklyViewModel

val core = module {
    singleOf(::ScrappingWebImpl) { bind<WebDataSource>() }
}

val repositories = module {
    singleOf(::RepositoryDBImp) {bind<RepositoryDB>()}
    single { WorkbookMonthsRepo(get(),get ()) }
}

val appViewModel = module { viewModelOf(::AppViewModel) }
val weekViewModel = module { viewModel { params -> WeeklyViewModel(params.get()) } }

