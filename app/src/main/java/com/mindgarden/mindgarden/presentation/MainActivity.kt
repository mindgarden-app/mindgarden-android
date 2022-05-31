package com.mindgarden.mindgarden.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.presentation.util.ext.hide
import com.mindgarden.mindgarden.presentation.util.ext.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingNavigation()
    }

    private fun settingNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.navView)

        val navController = findNavController(R.id.navHostHome)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.diaryListFragment -> navView.show()
                else -> navView.hide()
            }
        }
    }
}