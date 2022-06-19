package com.mindgarden.mindgarden.presentation.diarylist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.presentation.util.base.BaseFragment
import com.mindgarden.mindgarden.presentation.util.base.UIState
import com.mindgarden.mindgarden.presentation.writeDiary.WriteDiaryActivity
import com.mindgarden.mindgarden.presentation.util.common.base.BaseFragment
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toGardenDate
import com.mindgarden.mindgarden.util.ext.toStringOfPattern
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DiaryListFragment : BaseFragment<DiaryListViewModel, FragmentDiaryListBinding>(R.layout.fragment_diary_list) {
    override val viewModel: DiaryListViewModel by viewModels()
    private lateinit var diaryListAdapter : DiaryListAdapter
    var today = now().toGardenDate()

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("onviewcreated : ", "");

        //binding.tvDate.text = now().toGardenDateString()
        binding.tvDate.text = today.format(DateTimeFormatter.ofPattern("yyyy-MM"))
        // 일기 목록 조회
        observeDiaryList(binding.tvDate.text.toString(), false)

        binding.rvDiaryList.apply {
            diaryListAdapter = DiaryListAdapter({
                val intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
                startActivity(intent)
            }, { diary ->
                deleteDialog(diary)
            })
            /*
        // TODO: navigation 이용해서 view Diary 화면으로 이동할 수 있도록 해주세요
        diaryListAdapter = DiaryListAdapter({
           findNavController().navigate(DiaryListFragmentDirections.actionDiaryListFragmentToReadDiaryFragment(it))
        }, { diary ->
            deleteDialog(diary)
        })
            */

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
            /*
            var isasc = true;
            binding.imgSort.setOnClickListener {
                observeDiaryList(binding.tvDate.text.toString(), isasc)
                isasc = !isasc
            }
            */
        }

        binding.btnLeft.setOnClickListener {
            today = today.minusMonths(1)
            binding.tvDate.text = today.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            //binding.tvDate.text = now().toGardenDate().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"))
            observeDiaryList(binding.tvDate.text.toString(), false)
            Log.e("날짜 3. left 후", "")
        }

        binding.btnRight.setOnClickListener {
            today = today.plusMonths(1)
            binding.tvDate.text = today.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            //binding.tvDate.text = now().toGardenDate().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"))
            observeDiaryList(binding.tvDate.text.toString(), false)
            Log.e("날짜 4. right 후", "")
        val btnWrite: Button = binding.btnLoad
        btnWrite.setOnClickListener {
//            val intent : Intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
//            startActivity(intent)
            viewModel.loadDiaryList(
                now().toStringOfPattern(getString(R.string.pattern_calendar)),
                false
            )
        }
    }

    private fun observeDiaryList(date : String, asc : Boolean) {
        viewModel.loadDiaryList(date, asc)

        // 빈 화면
        viewModel.state.asLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UIState.Success -> {
                    if (it.data.size == 0) {
                        binding.llListZero.visibility = View.VISIBLE
                    } else {
                        binding.llListZero.visibility = View.GONE
                        diaryListAdapter.submitList(it.data)
                    }
                }
            }
        })

        Log.e("observe 후에 : ", binding.tvDate.text.toString())
    }

    private fun deleteDialog(diary: Diary) {
        val dlg = DiaryListDeleteDialog(this@DiaryListFragment.context!!)
        dlg.listener = object : DiaryListDeleteDialog.DiaryListDeleteDialogClickedListener {
            override fun onDeleteClicked() {
                //lifecycleScope.launch(Dispatchers.IO){ viewModel.deleteDiary(diary.idx) }
                viewModel.deleteDiary(diary.idx)
                Log.e("날짜 5. 삭제 후", "")
            }
        }
        dlg.start()
    }
}