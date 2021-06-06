package com.mindgarden.mindgarden.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.diarylist.DiaryListViewModel
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.mindgarden.mindgarden.factory.DiaryListViewModelFactory
import com.mindgarden.mindgarden.model.Diary

class AddActivity : AppCompatActivity() {
    //private lateinit var diaryListViewModel: DiaryListViewModel
    private val diaryListViewModel by viewModels<DiaryListViewModel> {
        DiaryListViewModelFactory(application) }
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //diaryListViewModel = ViewModelProvider(this).get(DiaryListViewModel::class.java)

        //val root = inflater.inflate(R.layout.activity_add, container, false)
        //val btnDone: Button = root.findViewById(com.mindgarden.mindgarden.R.id.btn_done)
        //val edtContent: EditText = root.findViewById(com.mindgarden.mindgarden.R.id.edt_add_content)

        val btnDone: Button = findViewById(R.id.btn_done)
        val edtContent: EditText = findViewById(R.id.edt_add_content)

        btnDone.setOnClickListener {
            val content = edtContent.text.toString()

            if (content.isEmpty()) {
                android.widget.Toast.makeText(this, "Enter", android.widget.Toast.LENGTH_SHORT).show()
            } else {
                val diary = com.mindgarden.mindgarden.model.Diary(content)
                diaryListViewModel.insert(diary)
                finish()
            }
        }
    }
}