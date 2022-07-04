package com.mindgarden.mindgarden.presentation.diarylist

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import com.mindgarden.mindgarden.R

class DiaryListDeleteDialog(context: Context) {
    lateinit var listener: DiaryListDeleteDialogClickedListener
    lateinit var txtDelete: TextView
    lateinit var txtCancel: TextView

    interface DiaryListDeleteDialogClickedListener {
        fun onDeleteClicked()
    }

    private val dlg = Dialog(context)

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setContentView(R.layout.layout_diary_list_delete_dialog)

        txtDelete = dlg.findViewById(R.id.txt_delete)
        txtDelete.setOnClickListener {
            listener.onDeleteClicked()
            dlg.dismiss()
        }

        txtCancel = dlg.findViewById(R.id.txt_cancel)
        txtCancel.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }
}