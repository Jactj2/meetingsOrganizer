package org.yomasoftware.project.ui.elements.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.yomasoftware.project.ui.elements.molecule.ItemType
import org.yomasoftware.resources.Res
import org.yomasoftware.resources.download_for_offline_24px
import org.yomasoftware.resources.icon_living
import org.yomasoftware.resources.offline_pin_24px

@Composable
fun TextTitleAssignament(
    text: String,
    color: Color,
) {
    Text(
        text,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
        color = color

    )
}

@Composable
fun TrailingIcon(state: ItemType, onClickButton: () -> Unit) {
    val imageIcon = when (state) {
        ItemType.ForDonwload -> Res.drawable.offline_pin_24px
        ItemType.Donwloading -> Res.drawable.icon_living
        ItemType.Download -> Res.drawable.download_for_offline_24px
    }
    IconButton(onClickButton) {
        Image(painterResource(imageIcon), contentDescription = "Cancelar la descarga")
    }
}