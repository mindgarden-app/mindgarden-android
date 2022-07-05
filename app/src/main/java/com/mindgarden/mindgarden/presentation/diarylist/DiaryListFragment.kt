package com.mindgarden.mindgarden.presentation.diarylist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.presentation.util.common.MainCalendarActivity
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toGardenDate
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@AndroidEntryPoint
class DiaryListFragment : NavigationFragment<DiaryListViewModel, FragmentDiaryListBinding>(R.layout.fragment_diary_list) {
    override val viewModel: DiaryListViewModel by viewModels()
    private val diaryListAdapter: DiaryListAdapter by lazy {
        DiaryListAdapter(
            { viewModel.goReadDiaryFragment(it) },
            { diary -> showDeleteDialog(diary) }
        )
    }

    var today = now().toGardenDate()
    private lateinit var getResult: ActivityResultLauncher<Intent>

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDiaryList.apply {
            adapter = diaryListAdapter

            // 일기 개별 삭제 - 스와이프
            val swipeHelperCallBack = SwipeHelperCallBack().apply { setClamp(170f) }
            val itemTouchHelper = ItemTouchHelper(swipeHelperCallBack)
            itemTouchHelper.attachToRecyclerView(this)

            setOnTouchListener { _, _ ->
                swipeHelperCallBack.removePreviousClamp(this)
                false
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

    override fun observeData() {
        // TODO : databinding xml에서 활용해보기
        this.lifecycleScope.launch {
            viewModel.date.collect {
                binding.toolbar.toolbarTitle.text = it.toStringOfPattern(getString(R.string.pattern_toolbar_diary_list))

                // 오른쪽 화살표 회색 처리
                if (!it.toStringOfPattern(getString(R.string.pattern_calendar)).equals(today.toStringOfPattern(getString(R.string.pattern_calendar)))) {
                    binding.toolbar.toolbarRightButton.setImageResource(R.drawable.btn_month_right)
                } else {
                    binding.toolbar.toolbarRightButton.setImageResource(R.drawable.btn_right_changed)
                }
            }
        }
    }

    private fun showDeleteDialog(diary: Diary) {
        val dlg = DiaryListDeleteDialog(this@DiaryListFragment.context!!)
        dlg.listener = object : DiaryListDeleteDialog.DiaryListDeleteDialogClickedListener {
            override fun onDeleteClicked() {
                viewModel.deleteDiary(diary)
            }
        }
        dlg.start()
    }
}