package org.yomasoftware.project.ui.elements.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.yomasoftware.project.ui.elements.molecule.ItemMonth
import org.yomasoftware.project.ui.elements.molecule.ItemMonthHeader
import org.yomasoftware.project.ui.elements.molecule.ItemType
import org.yomasoftware.project.ui.models.MonthWorkbook
import org.yomasoftware.project.ui.viewmodel.AppViewModel
import org.yomasoftware.project.ui.viewmodel.MonthsUiState

@Composable
fun WorkbookMonthsUI(
    isFirst: Boolean,
    firstOnChange: (Boolean) -> Unit,
    appViewModel: AppViewModel = koinInject(),
    navigationClick: (MonthWorkbook) -> Unit,
) {
    val monthsUiState by appViewModel.monthsUiState.collectAsState()

    val snackBarState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    when (monthsUiState) {
        MonthsUiState.Loading -> LoadingScreen()
        is MonthsUiState.Success ->
            WoorkBooksMonthsUI(
                appViewModel,
                (monthsUiState as MonthsUiState.Success).sourceList,
                snackBarState,
                onItemClick = { navigationClick(it) },
            ) {
                TODO()
//                appViewModel.downloadMonthInfo(it.title, "")
//                    .catch { message -> message.message?.let { it1 -> snackBarState.showSnackbar(it1) } }
            }
    }


    LaunchedEffect(Unit) {
        println("IS FISRT OPEN : $isFirst")
        if (isFirst) {
            scope.launch {
                snackBarState.showSnackbar(
                    "Actualizando la base de datos",
                    duration = SnackbarDuration.Short
                )
            }

            val updateWoorkbook =
                async(start = CoroutineStart.LAZY) { appViewModel.checkUpdateWorkbooks() }
            updateWoorkbook
                .invokeOnCompletion {
                    scope.launch {
                        snackBarState.showSnackbar(
                            "Base de datos actualizada",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            updateWoorkbook.invokeOnCompletion { firstOnChange(false) }
            updateWoorkbook.start()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WoorkBooksMonthsUI(
    viewModelApp: AppViewModel,
    sourceList: List<MonthWorkbook>,
    snackbarState: SnackbarHostState,
    onItemClick: (MonthWorkbook) -> Unit,
    downloadMonth: (MonthWorkbook) -> Flow<Float>,
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Guia de actividades",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) }
    ) { padingValues ->

        LazyColumn(
            Modifier.padding(horizontal = 16.dp),
            contentPadding = padingValues
        ) {
            val sourceGroupList = sourceList.groupBy { it.date.year }

            sourceGroupList.forEach { entry ->
                stickyHeader {
                    ItemMonthHeader(entry.key.toString())
                }

                items(items = entry.value, key = { item -> item.id }) { montItem ->
                    var progress by rememberSaveable { mutableFloatStateOf(0.0f) }
                    var view by rememberSaveable { mutableStateOf(false) }
                    var type by rememberSaveable { mutableStateOf(ItemType.ForDonwload) }
                    ItemMonth(montItem, progress, view, type, {}) {

//                        if (montItem.isDownload) return@ItemMonth onItemClick(montItem)
//
//                        scope.launch {
//                            downloadMonth(montItem)
//                                .onStart { type = ItemType.Donwloading }
//                                .onCompletion {
//                                    view = false
//
//                                    it.let {
//                                        snackbarState.showSnackbar("${montItem.title} descarga completa")
//                                    }
//                                }
//                                .collect { progress += it }
//                        }

                    }
                    Divider()
                }
            }
        }


    }
}


@Composable
fun LoadingScreen() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.onSurface,
        )
    }
}


