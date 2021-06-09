package com.mindgarden.mindgarden.ui.nickname

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.Injection

class NickNameActivity : AppCompatActivity() {
    private val viewModel: NickNameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nick_name)

        initBinding(this)
    }

    fun initBinding(context:Context){
        viewModel.repository = Injection.provideMindGardenRepository(context)
    }

}