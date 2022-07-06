package com.mindgarden.mindgarden.presentation.inventory

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.domain.usecase.diary.GetDiaryCountUseCase
import com.mindgarden.mindgarden.domain.usecase.garden.GetMindCountUseCase
import com.mindgarden.mindgarden.domain.usecase.garden.LoadGardenUseCase
import com.mindgarden.mindgarden.domain.usecase.garden.PlantMindUseCase
import com.mindgarden.mindgarden.presentation.inventory.model.GardenType
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind.Companion.convertInventoryMind
import com.mindgarden.mindgarden.presentation.inventory.model.InventoryMind.Companion.convertMind
import com.mindgarden.mindgarden.presentation.inventory.model.Tree
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbar
import com.mindgarden.mindgarden.presentation.util.common.GardenToolbarListener
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationViewModel
import com.mindgarden.mindgarden.util.Result
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InventoryViewModel @AssistedInject constructor(
    @Assisted val date: String,
    private val loadGardenUseCase: LoadGardenUseCase,
    private val plantMindUseCase: PlantMindUseCase,
    private val getDiaryCountUseCase: GetDiaryCountUseCase,
    private val getMindCountUseCase: GetMindCountUseCase,
    private val application: Application
) : NavigationViewModel() {

    private val gardenMap: HashMap<Int, InventoryMind> = LinkedHashMap()
    private val _garden = MutableStateFlow<List<InventoryMind>>(emptyList())
    val garden: StateFlow<List<InventoryMind>> = _garden
    private var mind: Mind? = null
    private var tree: Tree = Tree.Tree1
    private val currentDate by lazy {
        now().toStringOfPattern(application.getString(R.string.pattern_diary))
    }

    init {
        val gardenLocationArray = application.resources.getIntArray(R.array.garden_location_array)
        gardenLocationArray.forEach { location ->
            when (location) {
                13, 18, 19, 24 -> gardenMap[location] =
                    InventoryMind.from(location, GardenType.LAKE)
                else -> gardenMap[location] = InventoryMind.from(location, GardenType.EMPTY)
            }
        }
        _garden.value = gardenMap.values.toList()
    }

    fun loadGarden() = viewModelScope.launch {
        loadGardenUseCase.invoke(date).collect { result ->
            when (result) {
                is Result.Error -> {
                    Log.d("InventoryViewModel", "error - ${result.errorMsg} ")
                }
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        result.data
                            .map { it.convertInventoryMind() }
                            .forEach { gardenMap[it.location] = it }
                        _garden.value = gardenMap.values.toList()
                    } else {
                        clickGarden(1)  //default click tree
                    }
                }
            }
        }
    }

    fun clickGarden(location: Int) {
        gardenMap[location] = InventoryMind(location, tree, now(), GardenType.EMPTY)
        mind = gardenMap[location]?.convertMind()
        _garden.value = gardenMap.values.toList()
    }

    fun removeTree(location: Int) {
        gardenMap[location] = InventoryMind(location, null, now(), GardenType.EMPTY)
        _garden.value = gardenMap.values.toList()
    }

    fun clickTree(tree: Tree) {
        this.tree = tree
        mind?.let {
            clickGarden(it.location)
        }
    }

    private fun checkValid() {
        viewModelScope.launch(Dispatchers.IO) {
            val diaryCount = getDiaryCountUseCase.invoke(currentDate)
            val mindCount = getMindCountUseCase.invoke(currentDate)

            if (diaryCount < 1) {
                withContext(Dispatchers.Main) {
                    showToast(application.applicationContext, R.string.inventory_comment_5_3_1)
                }
                cancel()
            }

            if (mindCount > 0) {
                withContext(Dispatchers.Main) {
                    showToast(application.applicationContext, R.string.inventory_comment_5_4_1)
                }
                cancel()
            }

            plantMind()
        }
    }

    private suspend fun plantMind() = runCatching {
        mind?.let {
            plantMindUseCase.invoke(it)
        }
    }.onSuccess {
        Log.d("InventoryViewModel", "plantMind success: $it")
        navigateBack()
    }.onFailure {
        Log.d("InventoryViewModel", "plantMind failure: $it")
    }


    // TODO : 완료 버튼 활성화 기능
    val toolbarListener = object : GardenToolbarListener {
        override val toolbarData: GardenToolbar
            get() = GardenToolbar.InventoryToolbar()

        override fun leftButtonClick() {
            navigateBack()
        }

        override fun rightButtonClick() {
            checkValid()
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(date: String): InventoryViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            initData: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(initData) as T
            }
        }
    }
}