package com.mindgarden.mindgarden.presentation.writeDiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.presentation.diarylist.DiaryListViewModel
import androidx.activity.viewModels
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class WriteDiaryActivity : AppCompatActivity() {
    val viewModel: DiaryListViewModel by viewModels()
    private lateinit var btnSave : Button
    private lateinit var etContent: EditText
    val date: LocalDateTime = now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary)

        btnSave = findViewById(R.id.btn_save)
        etContent = findViewById(R.id.et_write_content)

        clickSave();
    }

    fun clickSave() {
        btnSave.setOnClickListener {
            val content = etContent.text.toString()
            val diary = Diary(
                date, content, 1, "good", null
            )

            if (content.isEmpty()) {
                Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show()
            } else {
                finish()
            }
        }
    }
}