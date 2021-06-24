package com.mindgarden.mindgarden.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mindgarden.mindgarden.data.model.entity.Garden
import com.mindgarden.mindgarden.data.model.local.Tree
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenRepository
import com.mindgarden.mindgarden.util.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import java.util.*
import kotlin.collections.LinkedHashMap

class HomeViewModel(private val gardenRepository: GardenRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val date: Date = Calendar.getInstance().time

    init {
        plantTree()
    }

    private fun plantTree() {
        val mindList : MutableMap<Int, Tree> = LinkedHashMap()
        mindList[0] = Tree(date, 10, 2)
        mindList[1] = Tree(date, 7, 4)
        mindList[2] = Tree(date, 31, 9)
        Log.d("HomeViewModel", "date: $date")
        val gardenData = Garden(
            0, date, mindList
        )

        gardenRepository.plantTree(gardenData)
            .subscribe(
            {
                Log.d("HomeViewModel", "Success insert")
            }, {
                Log.e("HomeViewModel", "Error : $it")
            }
        ).addTo(compositeDisposable)
    }

    fun getGardenTest() {
        Log.d("HomeViewModel", "date: $date")

        gardenRepository.getGarden(date)
            .doOnSubscribe {
                // show loading progress
            }
            .subscribe(
                {
                    Log.d("HomeViewModel", "Garden date: ${it.date} tree: ${it.minds}")
                },
                {
                    Log.e("HomeViewModel", "Error : $it")
                }
            ).addTo(compositeDisposable)
    }
}