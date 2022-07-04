package com.mindgarden.mindgarden.presentation.util.common

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.presentation.diarylist.DiaryListFragment
import com.mindgarden.mindgarden.util.ext.now
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainCalendarActivity : AppCompatActivity() {
    private val cal = Calendar.getInstance()
    val today = now()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MainCalendar);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calendar)

        //val model = ViewModelProvider(this).get(DiaryListViewModel::class.java)

        init()
    }

    private fun init() {
        initDate()
        yearClick()
    }

    private fun initDate() {
        val date = intent.getStringExtra("toolbarDate")
        findViewById<TextView>(R.id.txt_year_main_cal).text = date!!.substring(0, 4)
        cal.set(Calendar.YEAR, date.substring(0, 4).toInt())
        cal.set(Calendar.MONTH, (date.substring(6, 8).toInt() - 1))

        if (cal.get(Calendar.YEAR).toString().equals(today.year.toString())) {
            findViewById<ImageView>(R.id.btn_right_main_cal).setImageResource(R.drawable.btn_right_changed)
        }

        monthClickControl()
        initMonthBackground()
    }

    private fun initMonthBackground() {
        findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>(cal.get(Calendar.MONTH).toString()).setBackgroundResource(R.drawable.round_green_btn)
        findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>(cal.get(Calendar.MONTH).toString()).setTextColor(Color.WHITE)
    }

    private fun yearClick() {
        val btnLeft = findViewById<ImageView>(R.id.btn_left_main_cal)
        btnLeft.setOnClickListener {
            cal.add(Calendar.YEAR, -1);
            findViewById<TextView>(R.id.txt_year_main_cal).text = cal.get(Calendar.YEAR).toString()

            if (!cal.get(Calendar.YEAR).toString().equals(today.year.toString())) {
                findViewById<ImageView>(R.id.btn_right_main_cal).setImageResource(R.drawable.btn_month_right)
            }

            monthClickControl()
            //initMonthBackground()
        }

        val btnRight = findViewById<ImageView>(R.id.btn_right_main_cal)
        btnRight.setOnClickListener {
            if (!cal.get(Calendar.YEAR).toString().equals(today.year.toString())) {
                cal.add(Calendar.YEAR, 1);
                findViewById<TextView>(R.id.txt_year_main_cal).text = cal.get(Calendar.YEAR).toString()
                findViewById<ImageView>(R.id.btn_right_main_cal).setImageResource(R.drawable.btn_month_right)
            }

            if (cal.get(Calendar.YEAR).toString().equals(today.year.toString())) {
                findViewById<ImageView>(R.id.btn_right_main_cal).setImageResource(R.drawable.btn_right_changed)
            }

            monthClickControl()
            //initMonthBackground()
        }
    }

    private fun monthClickControl() {
        resetClick()

        if (cal.get(Calendar.YEAR).toString().equals(today.year.toString())) {
            setMonthClick(0, today.monthValue - 1)
        } else {
            setMonthClick(0, 11)
        }
    }

    private fun resetClick() {
        //findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>(cal.get(Calendar.MONTH).toString()).setBackgroundResource(0)
        findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>(cal.get(Calendar.MONTH).toString()).setBackgroundResource(R.drawable.round_white_btn)
        findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>(cal.get(Calendar.MONTH).toString()).setTextColor(Color.rgb(107, 107, 107))

        for (i in 0..11) {
            findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>("$i").isClickable = false
        }
    }

    private fun setMonthClick(start: Int, end: Int) {
        for (i in start..end) {
            findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag<TextView>("$i").isClickable = true
            monthClick(findViewById<ConstraintLayout>(R.id.cl_main_cal).findViewWithTag("$i"))
        }
    }

    private fun monthClick(tv: TextView) {
        tv.setOnClickListener {
            resetClick()
            tv.setBackgroundResource(R.drawable.round_green_btn)
            tv.setTextColor(Color.WHITE)
            val monthText = tv.text.toString()
            cal.set(Calendar.MONTH, (monthText.toInt() - 1))
            intentToFragment()
        }
    }

    private fun intentToFragment() {
        Intent(this, DiaryListFragment::class.java).apply {
            putExtra("year", cal.get(Calendar.YEAR))
            putExtra("month", cal.get(Calendar.MONTH))
            setResult(Activity.RESULT_OK,this)
            finish()
        }
    }
}