package org.yomasoftware.project.ui.elements.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parameterArrayOf
import org.yomasoftware.project.data.models.WeeklyWorkbookMeetings
import org.yomasoftware.project.ui.elements.TextAssigned
import org.yomasoftware.project.ui.elements.molecule.ItemsMeetingPart
import org.yomasoftware.project.ui.elements.atom.TextTitleAssignament
import org.yomasoftware.project.ui.elements.molecule.TopNavigationBar
import org.yomasoftware.project.ui.elements.atom.selecterColorMeeting
import org.yomasoftware.project.ui.elements.theme.lectureColor
import org.yomasoftware.project.ui.viewmodel.WeekAssignamentsState
import org.yomasoftware.project.ui.viewmodel.WeeklyViewModel
import org.yomasoftware.resources.Res
import org.yomasoftware.resources.icon_tesaures
import org.yomasoftware.resources.introduction
import org.yomasoftware.resources.music_note
import org.yomasoftware.resources.one_min
import sqldelight.meetingsdb.data.MeetingPart


@Composable
fun WeekAssignView(
    idWeek: Long,
    titleWeek: String,
    modelView: WeeklyViewModel =
        koinInject(parameters =
        { parameterArrayOf(idWeek) }
        ),
    onBack: () -> Unit,
) {

    val weekState by modelView.weeklyAssigState.collectAsState()

    Scaffold(
        topBar = { TopNavigationBar(titleWeek, onBack) }
    ) { padingValues ->
        when (weekState) {
            WeekAssignamentsState.Loading -> LoadingScreen()
            is WeekAssignamentsState.Success -> WeekAssignListView(
                (weekState as WeekAssignamentsState.Success).week,
                padingValues
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeekAssignListView(
    sourceData: WeeklyWorkbookMeetings,
    padingValues: PaddingValues,
    iconSong: @Composable() (() -> Unit) =
        { Icon(painterResource(Res.drawable.music_note), "") },
    oneMinuteText: String = stringResource(Res.string.one_min),
    introductionText: String = stringResource(Res.string.introduction),
    iconTreasures: @Composable() (() -> Unit) =
        { Icon(painterResource(Res.drawable.icon_tesaures), "") },
) {
    LazyColumn(
        Modifier.widthIn(max = 600.dp).padding(horizontal = 16.dp),
        contentPadding = padingValues,
        horizontalAlignment = Alignment.Start
    ) {

        item {
            Row(modifier = Modifier.fillParentMaxWidth()) {
                Column {
                    ListItem(
                        text = {
                            Text(
                                sourceData.title,
                                style = MaterialTheme.typography.h4
                            )
                        }
                    )
                    ListItem(
                        text = {
                            Text(
                                sourceData.weeklyReading,
                                style = MaterialTheme.typography.h5,
                                color = lectureColor
                            )
                        }
                    )
                    Box(modifier = Modifier.fillMaxSize().background(Color.Blue))
                }
            }
            Divider()
            ListItem(
                icon = iconSong
            ) {
                Text(
                    sourceData.songs[0],
                    style = MaterialTheme.typography.body1
                )
            }
            ListItem(
                icon = { Text(oneMinuteText, style = MaterialTheme.typography.subtitle2) }
            ) {
                Text(introductionText, style = MaterialTheme.typography.body1)
            }
        }

        sourceData.assignamentList.forEach { (meetingPart, assignamentsWeekly) ->
            val color = selecterColorMeeting(meetingPart)
            item {
                ItemsMeetingPart(meetingPart, color)
                Divider(color = color)
                if (meetingPart == MeetingPart.LIVING_AS_CHRISTIANS)
                    ListItem(
                        icon = iconSong
                    ) {
                        Text(
                            sourceData.songs[1],
                            style = MaterialTheme.typography.body2
                        )
                    }

            }

            items(assignamentsWeekly) {
                ListItem(
                    modifier = Modifier.widthIn(min = 300.dp, max = 600.dp),
                    icon = { Text("") },
                    text = { TextTitleAssignament(it.title, color) },
                    secondaryText = { TextAssigned(it.name) }
                )
            }
        }

        item {
            ListItem(
                icon = { Text("3 Min", style = MaterialTheme.typography.subtitle2) }
            ) {
                Text(
                    "Palabras de conclusión",
                    style = MaterialTheme.typography.body1
                )
            }
            ListItem(
                icon = iconSong
            ) {
                Text(
                    sourceData.songs[2],
                    style = MaterialTheme.typography.body1
                )
            }
        }


    }
}