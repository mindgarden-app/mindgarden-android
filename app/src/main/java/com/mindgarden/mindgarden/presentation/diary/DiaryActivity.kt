package com.mindgarden.mindgarden.presentation.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ActivityDiaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        settingNavigation()
        setToolbarData()
    }

    private fun settingNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.DiaryNavHost)
        val navController = navHost?.findNavController()
    }

    /**
     * 현재 fragment에 따라서 값 다르게 하기
     */
    private fun setToolbarData() {
        val navHost = supportFragmentManager.findFragmentById(R.id.DiaryNavHost)
        val navController = navHost?.findNavController()

        Log.d("DiaryActivity", "setToolbarData: ${navController?.currentDestination}")

//        binding.toolbar.apply {
//            toolbarTitle.text = "타이틀임"
//        }
    }
}