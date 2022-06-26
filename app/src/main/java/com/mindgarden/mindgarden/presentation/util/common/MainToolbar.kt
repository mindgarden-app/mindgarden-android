package com.mindgarden.mindgarden.presentation.util.common

import com.mindgarden.mindgarden.R

sealed class MainToolbar {
    abstract val leftButtonImg: Int
    abstract var title: String
    abstract val rightButtonImg: Int
    abstract val sortButtonImg: Int?

    data class DiaryListToolbar(
        override val leftButtonImg: Int = R.drawable.btn_month_left,
        override var title: String = "",
        override val rightButtonImg: Int = R.drawable.btn_month_right,
        override val sortButtonImg: Int? = R.drawable.btn_order
    ) : MainToolbar()
}

interface MainToolbarListener {
    val toolbarData: MainToolbar
    fun leftButtonClick()
    fun rightButtonClick()
    fun sortButtonClick()
}