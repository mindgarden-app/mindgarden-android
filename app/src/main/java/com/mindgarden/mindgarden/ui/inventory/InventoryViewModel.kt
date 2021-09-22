package com.mindgarden.mindgarden.ui.inventory

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.data.model.Result
import com.mindgarden.mindgarden.data.repository.gardenRepo.GardenRepository
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind.Companion.convertInventoryMind
import com.mindgarden.mindgarden.ui.inventory.model.InventoryTree
import com.mindgarden.mindgarden.ui.util.base.BaseViewModel
import com.mindgarden.mindgarden.util.ext.now
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.kotlin.addTo
import java.time.LocalDateTime

class InventoryViewModel @AssistedInject constructor(
    private val gardenRepository: GardenRepository,
    @Assisted private val initData: InventoryData
) : BaseViewModel() {
    private val _trees = MutableLiveData<MutableList<InventoryTree>>()
    val trees: LiveData<MutableList<InventoryTree>> get() = _trees

    fun initInventoryTrees() {
        val items = mutableListOf<InventoryTree>()
        for(i in 0 until initData.treeResArray.length()) {
            items.add(InventoryTree(i, initData.treeResArray.getResourceId(i, -1)))
        }
        _trees.value = items
    }

    private val _garden = MutableLiveData<List<InventoryMind>>()
    val garden: LiveData<List<InventoryMind>> get() = _garden

    @SuppressLint("NullSafeMutableLiveData")
    fun initGarden() {
        getGarden()
            .subscribe(
                {
                    when (it) {
                        is Result.Error -> _garden.postValue(getDefaultGarden())
                        is Result.Success -> _garden.value = it.data
                    }
                },
                {
                    // error
                    Log.e("InventoryViewModel", "[subscribeOn] error get data: ${it.message}")
                },
            )
            .addTo(compositeDisposable)
    }

    private fun getGarden() = gardenRepository.getGarden(initData.gardenDate)
        .doOnSubscribe { Log.i("InventoryViewModel", "[doOnSubscribe] show loading view") }
        .map {
            if (it.isNullOrEmpty()) Result.Error("Empty garden")
            else{
                val list = it.map { mind -> mind.copy().convertInventoryMind() }
                Result.Success(list)
            }
        }


    private fun getDefaultGarden(): MutableList<InventoryMind> {
        val location = initData.locationResArray
        val items = mutableListOf<InventoryMind>()
        location.forEach { location : Int ->
            when (location) {
                13, 18, 19, 24 -> items.add(InventoryMind(gardenDate = initData.gardenDate, date = now(),
                    location = location, type = GardenType.RIVER))
                else -> items.add(InventoryMind(gardenDate = initData.gardenDate, date = now(),
                    location = location, type = GardenType.EMPTY))
            }
        }
        return items
    }

    fun plant() {

    }

    interface InventoryData {
        val gardenDate: LocalDateTime
        val treeResArray: TypedArray
        val locationResArray: IntArray
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(initData: InventoryData): InventoryViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            initData: InventoryData
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(initData) as T
            }
        }
    }
}