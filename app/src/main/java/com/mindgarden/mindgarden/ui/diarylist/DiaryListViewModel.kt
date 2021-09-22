package com.mindgarden.mindgarden.ui.diarylist

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mindgarden.mindgarden.data.model.entity.Diary
import com.mindgarden.mindgarden.data.model.entity.Garden
import com.mindgarden.mindgarden.data.model.local.Tree
import com.mindgarden.mindgarden.data.repository.diaryRepo.DefaultDiaryRepository
import com.mindgarden.mindgarden.data.repository.gardenRepo.DefaultGardenRepository
import com.mindgarden.mindgarden.ui.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.*
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val diaryRepository: DefaultDiaryRepository) : BaseViewModel() {
    val date: Date = Calendar.getInstance().time

    init {
        //getDiaries()
        //deleteDiary()

        // TODO : 조회 1차 작업
        getAll()
    }

    fun getDiaries(): Flowable<List<Diary>> {
        diaryRepository.getDiaries(date).subscribe(
                {
                    Log.d("목록", "list success : $it")
                }, {
            Log.e("목록", "Error : $it")
        }
        ).addTo(compositeDisposable)

        return diaryRepository.getDiaries(date)
    }

    // TODO : 조회 1차 작업
    fun getAll(): Flowable<List<Diary>> {
        diaryRepository.getAll().subscribe(
            {
                Log.d("목록", "list success : $it")
            }, {
                Log.e("목록", "Error : $it")
            }
        ).addTo(compositeDisposable)

        return diaryRepository.getAll()
    }

    fun writeDiary(diary: Diary) {
        diaryRepository.writeDiary(diary)
            .subscribe(
                {
                    Log.d("DiaryListViewModel", "Success write")
                }, {
                    Log.e("DiaryListViewModel", "Error : $it")
                }
            ).addTo(compositeDisposable)
    }

    fun deleteDiary(diary : Diary) {
        diaryRepository.deleteDiary(diary)
            .subscribe(
                {
                    Log.d("목록", "Success delete")
                }, {
                    Log.e("목록", "Error : $it")
                }
            ).addTo(compositeDisposable)
    }
}