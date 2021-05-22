package com.mindgarden.mindgarden

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.databinding.ActivityMainBinding
import com.mindgarden.mindgarden.model.User
import com.mindgarden.mindgarden.viewModel.MainViewModel
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        subscribeUi(binding)
    }

    private fun subscribeUi(binding: ActivityMainBinding) {
        //var factory = MainViewModelFactory()
        //var viewModel: MainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        var viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.clickConverter.observe(this, Observer {
            Toast.makeText(this, "${binding.user?.name}, ${binding.user?.address}", Toast.LENGTH_SHORT).show()
        })

        binding.user = User("May", "Seoul", R.drawable.profile_sample)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }
}