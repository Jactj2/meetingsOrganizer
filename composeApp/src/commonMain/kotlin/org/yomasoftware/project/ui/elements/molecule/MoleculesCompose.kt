package org.yomasoftware.project.ui.elements.molecule

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.yomasoftware.project.data.models.ui.MonthWorkbook
import org.yomasoftware.project.ui.elements.atom.TrailingIcon
import org.yomasoftware.resources.Res
import org.yomasoftware.resources.icon_field_ministry
import org.yomasoftware.resources.icon_living
import org.yomasoftware.resources.icon_tesaures
import org.yomasoftware.resources.metting_part1
import org.yomasoftware.resources.metting_part2
import org.yomasoftware.resources.metting_part3
import sqldelight.meetingsdb.data.MeetingPart


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsMeetingPart(
    meetingPart: MeetingPart,
    color: Color,
    titleMeeting: String? = null,
    imageRes: DrawableResource? = null,
) {
    val internalTitle: String
    val internalResource: DrawableResource

    when (meetingPart) {
        MeetingPart.TREASURES_FROM_GODS_WORD -> {
            internalTitle = stringResource(Res.string.metting_part1)
            internalResource = Res.drawable.icon_tesaures
        }

        MeetingPart.APPLY_YOURSELF_TO_THE_FIELD_MINISTRY -> {
            internalTitle = stringResource(Res.string.metting_part2)
            internalResource = Res.drawable.icon_field_ministry
        }

        MeetingPart.LIVING_AS_CHRISTIANS -> {

            internalTitle = stringResource(Res.string.metting_part3)
            internalResource = Res.drawable.icon_living
        }
    }

    ListItem(
        icon = { Image(painterResource(imageRes ?: internalResource), "") }
    ) {
        Text(
            titleMeeting ?: internalTitle,
            style = MaterialTheme.typography.h5,
            color = color,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun CustomLinearProgres(progress: Float) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = "label"
    )

    if (animatedProgress < 0.1f || animatedProgress > 1f)
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
        ) else
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { progress },
        )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopNavigationBar(
    title: String,
    onback: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onback) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, "")
            }
        }
    )
}

@Composable
fun ItemMonthHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.h6,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemMonth(
    itemData: MonthWorkbook,
    progress: Float,
    viewBar: Boolean,
    itemType: ItemType,
    onClickTrailing: () -> Unit,
    onClickItem: () -> Unit,
) {

    Column(
        modifier = Modifier.clickable(onClick = onClickItem)
    ) {
        ListItem(
            modifier = Modifier.height(64.dp),
            text = { Text(itemData.title, style = MaterialTheme.typography.h6) },
            secondaryText = { Text(itemData.subtitle, style = MaterialTheme.typography.body1) },
            trailing = { TrailingIcon(itemType) { if (itemType == ItemType.Donwloading) onClickTrailing } },
        )

        if (viewBar) CustomLinearProgres(progress)
    }
}

enum class ItemType {
    ForDonwload, Donwloading, Download
}