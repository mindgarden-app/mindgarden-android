package com.mindgarden.mindgarden.presentation.util.common

import com.mindgarden.mindgarden.R

sealed class GardenToolbar {
    abstract val leftButtonImg: Int
    abstract var title: String
    abstract val rightButtonColor: Int
    abstract val rightButtonText: Int

    data class WriteDiaryToolbar(
        override val leftButtonImg: Int = R.drawable.ic_btn_back,
        override var title: String = "",
        override val rightButtonColor: Int = R.color.garden_green,
        override val rightButtonText: Int = R.string.garden_toolbar_right_write,
    ) : GardenToolbar()

    data class WeatherToolbar(
        override val leftButtonImg: Int = R.drawable.ic_btn_close,
        override var title: String = "기분 선택",
        override val rightButtonColor: Int = R.color.garden_green,
        override val rightButtonText: Int = R.string.garden_toolbar_right_complete
    ) : GardenToolbar()

    data class ReadDiaryToolbar(
        override val leftButtonImg: Int = R.drawable.ic_btn_back,
        override var title: String = "",
        override val rightButtonColor: Int = R.color.gray_600,
        override val rightButtonText: Int = R.string.garden_toolbar_right_Modify
    ) : GardenToolbar()

    data class InventoryToolbar(
        override val leftButtonImg: Int = R.drawable.ic_btn_back,
        override var title: String = "나무 심기",
        override val rightButtonColor: Int = R.color.garden_green,
        override val rightButtonText: Int = R.string.garden_toolbar_right_complete
    ) : GardenToolbar()
}

interface GardenToolbarListener {
    val toolbarData: GardenToolbar
    fun leftButtonClick()
    fun rightButtonClick()
}