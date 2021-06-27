package com.mindgarden.mindgarden.ui.writeDiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.diarylist.DiaryListViewModel
import androidx.activity.viewModels
import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.ui.diarylist.DiaryListViewModelFactory

class WriteDiaryActivity : AppCompatActivity() {
    //private lateinit var diaryListViewModel: DiaryListViewModel
    private val diaryListViewModel by viewModels<DiaryListViewModel> {
        DiaryListViewModelFactory(application) }
    private var id: Long? = null
    private lateinit var btnSave : Button
    private lateinit var etContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary)

        //diaryListViewModel = ViewModelProvider(this).get(DiaryListViewModel::class.java)

        //val root = inflater.inflate(R.layout.activity_add, container, false)
        //val btnDone: Button = root.findViewById(com.mindgarden.mindgarden.R.id.btn_done)
        //val edtContent: EditText = root.findViewById(com.mindgarden.mindgarden.R.id.edt_add_content)

        btnSave = findViewById(R.id.btn_save)
        etContent = findViewById(R.id.et_write_content)

        clickSave();
    }

    fun clickSave() {
        btnSave.setOnClickListener {
            val content = etContent.text.toString()

            if (content.isEmpty()) {
                Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show()
            } else {
                val diary = Diary(content)
                diaryListViewModel.insert(diary)
                finish()
            }
        }
    }
}