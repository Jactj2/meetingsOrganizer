@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package org.yomasoftware.resources

import kotlin.OptIn
import org.jetbrains.compose.resources.DrawableResource

private object CommonMainDrawable0 {
  public val compose_multiplatform: DrawableResource by 
      lazy { init_compose_multiplatform() }

  public val download_for_offline_24px: DrawableResource by 
      lazy { init_download_for_offline_24px() }

  public val icon_cancel_circle: DrawableResource by 
      lazy { init_icon_cancel_circle() }

  public val icon_field_ministry: DrawableResource by 
      lazy { init_icon_field_ministry() }

  public val icon_living: DrawableResource by 
      lazy { init_icon_living() }

  public val icon_tesaures: DrawableResource by 
      lazy { init_icon_tesaures() }

  public val music_note: DrawableResource by 
      lazy { init_music_note() }

  public val offline_pin_24px: DrawableResource by 
      lazy { init_offline_pin_24px() }
}

public val Res.drawable.compose_multiplatform: DrawableResource
  get() = CommonMainDrawable0.compose_multiplatform

private fun init_compose_multiplatform(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:compose_multiplatform",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/compose-multiplatform.xml", -1, -1),
    )
)

public val Res.drawable.download_for_offline_24px: DrawableResource
  get() = CommonMainDrawable0.download_for_offline_24px

private fun init_download_for_offline_24px(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:download_for_offline_24px",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/download_for_offline_24px.xml", -1, -1),
    )
)

public val Res.drawable.icon_cancel_circle: DrawableResource
  get() = CommonMainDrawable0.icon_cancel_circle

private fun init_icon_cancel_circle(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:icon_cancel_circle",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/icon_cancel_circle.xml", -1, -1),
    )
)

public val Res.drawable.icon_field_ministry: DrawableResource
  get() = CommonMainDrawable0.icon_field_ministry

private fun init_icon_field_ministry(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:icon_field_ministry",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/icon_field_ministry.xml", -1, -1),
    )
)

public val Res.drawable.icon_living: DrawableResource
  get() = CommonMainDrawable0.icon_living

private fun init_icon_living(): DrawableResource = org.jetbrains.compose.resources.DrawableResource(
  "drawable:icon_living",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/icon_living.xml", -1, -1),
    )
)

public val Res.drawable.icon_tesaures: DrawableResource
  get() = CommonMainDrawable0.icon_tesaures

private fun init_icon_tesaures(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:icon_tesaures",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/icon_tesaures.xml", -1, -1),
    )
)

public val Res.drawable.music_note: DrawableResource
  get() = CommonMainDrawable0.music_note

private fun init_music_note(): DrawableResource = org.jetbrains.compose.resources.DrawableResource(
  "drawable:music_note",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/music_note.xml", -1, -1),
    )
)

public val Res.drawable.offline_pin_24px: DrawableResource
  get() = CommonMainDrawable0.offline_pin_24px

private fun init_offline_pin_24px(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:offline_pin_24px",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/drawable/offline_pin_24px.xml", -1, -1),
    )
)
