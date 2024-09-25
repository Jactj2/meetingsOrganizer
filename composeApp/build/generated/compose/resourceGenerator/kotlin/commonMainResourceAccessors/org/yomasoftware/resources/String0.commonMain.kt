@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package org.yomasoftware.resources

import kotlin.OptIn
import org.jetbrains.compose.resources.StringResource

private object CommonMainString0 {
  public val introduction: StringResource by 
      lazy { init_introduction() }

  public val metting_part1: StringResource by 
      lazy { init_metting_part1() }

  public val metting_part2: StringResource by 
      lazy { init_metting_part2() }

  public val metting_part3: StringResource by 
      lazy { init_metting_part3() }

  public val one_min: StringResource by 
      lazy { init_one_min() }

  public val three_min: StringResource by 
      lazy { init_three_min() }
}

public val Res.string.introduction: StringResource
  get() = CommonMainString0.introduction

private fun init_introduction(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:introduction", "introduction",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/values/strings.commonMain.cvr", 10, 56),
    )
)

public val Res.string.metting_part1: StringResource
  get() = CommonMainString0.metting_part1

private fun init_metting_part1(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:metting_part1", "metting_part1",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/values/strings.commonMain.cvr", 67, 49),
    )
)

public val Res.string.metting_part2: StringResource
  get() = CommonMainString0.metting_part2

private fun init_metting_part2(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:metting_part2", "metting_part2",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/values/strings.commonMain.cvr", 117, 53),
    )
)

public val Res.string.metting_part3: StringResource
  get() = CommonMainString0.metting_part3

private fun init_metting_part3(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:metting_part3", "metting_part3",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/values/strings.commonMain.cvr", 171, 53),
    )
)

public val Res.string.one_min: StringResource
  get() = CommonMainString0.one_min

private fun init_one_min(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:one_min", "one_min",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/values/strings.commonMain.cvr", 225, 23),
    )
)

public val Res.string.three_min: StringResource
  get() = CommonMainString0.three_min

private fun init_three_min(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:three_min", "three_min",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/org.yomasoftware.resources/values/strings.commonMain.cvr", 249, 25),
    )
)
