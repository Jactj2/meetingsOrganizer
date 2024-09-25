package org.yomasoftware.project.ui.elements.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yomasoftware.project.data.models.WeeklyWorkbookMeetings
import org.yomasoftware.project.domain.GetWeeksOfMonthCaseUse
import org.yomasoftware.project.ui.elements.molecule.TopNavigationBar
import org.yomasoftware.project.ui.viewmodel.ViewOfWeeksState


@Composable
fun WeeklyWorkbook(
    modifier: Modifier = Modifier,
    titleMonth: String,
    idMonth: Long,
    onItemClick: (WeeklyWorkbookMeetings) -> Unit,
    onBack: () -> Unit,
) {

    val uiState by produceState(
        initialValue = ViewOfWeeksState(isLoading = true, sourceList = listOf())
    ) {
        value = ViewOfWeeksState(sourceList = GetWeeksOfMonthCaseUse()(idMonth), isLoading = false)
    }

    Scaffold(
        topBar = { TopNavigationBar(titleMonth, onBack) },
    ) { padingValues ->

        when {
            uiState.sourceList.isNotEmpty() ->
                LazyColumn(
                    Modifier.padding(horizontal = 16.dp),
                    contentPadding = padingValues

                ) {
                    items(uiState.sourceList) {
                        ItemWeek(
                            itemData = it,
                            onItemClick
                        )
                        Divider()
                    }
                }

            uiState.isLoading -> LoadingScreen()
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemWeek(itemData: WeeklyWorkbookMeetings, onItemClick: (WeeklyWorkbookMeetings) -> Unit) {

    ListItem(
        modifier = Modifier.height(64.dp).clickable { onItemClick(itemData) },
        text = { Text(itemData.title, style = MaterialTheme.typography.h6) },
    )
}