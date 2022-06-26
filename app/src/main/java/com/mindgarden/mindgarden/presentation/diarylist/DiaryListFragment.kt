package com.mindgarden.mindgarden.presentation.diarylist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.presentation.util.common.MainCalendarActivity
import com.mindgarden.mindgarden.presentation.util.common.UIState
import com.mindgarden.mindgarden.presentation.util.common.base.BaseFragment
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toGardenDate
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@AndroidEntryPoint
class DiaryListFragment : BaseFragment<DiaryListViewModel, FragmentDiaryListBinding>(R.layout.fragment_diary_list) {
    override val viewModel: DiaryListViewModel by viewModels()
    private lateinit var diaryListAdapter : DiaryListAdapter

    var today = now().toGardenDate()
    private lateinit var getResult: ActivityResultLauncher<Intent>

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("onviewcreated : ", "");

        // 툴바 및 일기 목록 조회
        viewModel.date.asLiveData().observe(viewLifecycleOwner) {
            binding.toolbar.toolbarTitle.text = it.toStringOfPattern(getString(R.string.pattern_toolbar_diary_list))

            // 화살표 회색 처리
            if (!it.toStringOfPattern(getString(R.string.pattern_calendar)).equals(today.toStringOfPattern(getString(R.string.pattern_calendar)))) {
                binding.toolbar.toolbarRightButton.setImageResource(R.drawable.btn_month_right)
            } else {
                binding.toolbar.toolbarRightButton.setImageResource(R.drawable.btn_right_changed)
            }

            observeDiaryList(it.toStringOfPattern(getString(R.string.pattern_calendar)), false)
        }

        //viewModel.setToolDate(today)
        //binding.toolbar.toolbarTitle.text = today.toStringOfPattern(getString(R.string.pattern_toolbar_diary_list))
        //observeDiaryList(today.toStringOfPattern(getString(R.string.pattern_calendar)).toString(), false)

        // TODO: navigation 이용해서 view Diary 화면으로 이동할 수 있도록 해주세요
        binding.rvDiaryList.apply {
            diaryListAdapter = DiaryListAdapter({
                findNavController().navigate(DiaryListFragmentDirections.actionDiaryListFragmentToReadDiaryFragment(it))
            }, { diary ->
                deleteDialog(diary)
            })

            adapter = diaryListAdapter

            // 일기 개별 삭제 - 스와이프
            val swipeHelperCallBack = SwipeHelperCallBack().apply { setClamp(170f) }
            val itemTouchHelper = ItemTouchHelper(swipeHelperCallBack)
            itemTouchHelper.attachToRecyclerView(binding.rvDiaryList)

            setOnTouchListener { _, _ ->
                swipeHelperCallBack.removePreviousClamp(this)
                false
            }

            // 정렬
            var isListAsc = false;
            binding.toolbar.toolbarSortButton.setOnClickListener {
                isListAsc = !isListAsc
                observeDiaryList(viewModel.getToolbarDate().toStringOfPattern(getString(R.string.pattern_calendar)), isListAsc)
            }
        }

        binding.toolbar.toolbarRightButton.setOnClickListener {
            if (!viewModel.getToolbarDate().toStringOfPattern(getString(R.string.pattern_calendar)).equals(today.toStringOfPattern(getString(R.string.pattern_calendar)))) {
                binding.toolbar.listener?.rightButtonClick()
            }
        }

        // 달력 팝업
        binding.toolbar.toolbarTitle.setOnClickListener {
            val intent = Intent(requireActivity(), MainCalendarActivity::class.java).apply {
                putExtra("toolbarDate", binding.toolbar.toolbarTitle.text)
            }
            getResult.launch(intent)
        }

        var cal = Calendar.getInstance()
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                cal.set(Calendar.MONTH, it.data!!.getIntExtra("month", -1))
                cal.set(Calendar.YEAR, it.data!!.getIntExtra("year", -1))

                val tz: TimeZone = cal.getTimeZone()
                val zoneId: ZoneId = tz.toZoneId()
                val localDateTime = LocalDateTime.ofInstant(cal.toInstant(), zoneId)
                viewModel.setToolbarDate(localDateTime)
            }
        }
    }

    private fun observeDiaryList(date : String, isASC : Boolean) {
        viewModel.loadDiaryList(date, isASC)

        // 빈 화면
        viewModel.state.asLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UIState.Success -> {
                    if (it.data.size == 0) {
                        binding.llListZero.visibility = View.VISIBLE
                    } else {
                        binding.llListZero.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun deleteDialog(diary: Diary) {
        val dlg = DiaryListDeleteDialog(this@DiaryListFragment.context!!)
        dlg.listener = object : DiaryListDeleteDialog.DiaryListDeleteDialogClickedListener {
            override fun onDeleteClicked() {
                viewModel.deleteDiary(diary.idx)
            }
        }
        dlg.start()
    }
}