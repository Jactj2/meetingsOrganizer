package org.yomasoftware.project.ui.elements.atom

import org.yomasoftware.project.ui.elements.theme.fieldMinistryColor
import org.yomasoftware.project.ui.elements.theme.livingChristiansColor
import org.yomasoftware.project.ui.elements.theme.treasuresColor
import sqldelight.meetingsdb.data.MeetingPart

fun selecterColorMeeting(meeting: MeetingPart) = when (meeting) {
    MeetingPart.TREASURES_FROM_GODS_WORD -> treasuresColor
    MeetingPart.APPLY_YOURSELF_TO_THE_FIELD_MINISTRY -> fieldMinistryColor
    MeetingPart.LIVING_AS_CHRISTIANS -> livingChristiansColor
}