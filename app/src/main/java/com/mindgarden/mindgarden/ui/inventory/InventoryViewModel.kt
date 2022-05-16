package com.mindgarden.mindgarden.presentation.inventory

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.domain.repository.GardenRepository
import com.mindgarden.mindgarden.ui.inventory.model.GardenType
import com.mindgarden.mindgarden.ui.inventory.model.InventoryMind
import com.mindgarden.mindgarden.ui.inventory.model.InventoryTree
import com.mindgarden.mindgarden.ui.util.base.BaseViewModel
import com.mindgarden.mindgarden.util.ext.now
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDateTime

class InventoryViewModel @AssistedInject constructor(
    private val gardenRepository: GardenRepository,
    @Assisted private val initData: InventoryData
) : BaseViewModel() {
    private val itemMap: HashMap<Int, InventoryMind> = LinkedHashMap()

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
        setDefaultGarden(itemMap)
//        getGarden()
//            .subscribe(
//                {
//                },
//                {
//                    // error
//                    Log.e("InventoryViewModel", "[subscribeOn] error get data: $it")
//                },
//            )
//            .addTo(compositeDisposable)
    }

//    private fun getGarden() = gardenRepository.getGarden(initData.gardenDate)
//        .doOnSubscribe { Log.i("InventoryViewModel", "[doOnSubscribe] show loading view") }


    private fun setDefaultGarden(itemMap: HashMap<Int, InventoryMind>) {
        val location = initData.locationResArray

        location.forEach { location : Int ->
            when (location) {
                13, 18, 19, 24 -> {
                    itemMap[location] = InventoryMind(date = now(),
                        location = location, type = GardenType.RIVER)
                }
                else -> {
                    itemMap[location] = InventoryMind(date = now(),
                        location = location, type = GardenType.EMPTY)
                }
            }
        }
        _garden.value = itemMap.values.toList()
        Log.d("InventoryViewModel", "End set DefaultGarden")
    }

//    fun plant(mind: InventoryMind): Completable  {
//        Log.i("InventoryViewModel", "plant ${mind.convertMind()}")
//        return gardenRepository.plantTree(mind.convertMind())
//    }


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
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(initData) as T
            }
        }
    }
}