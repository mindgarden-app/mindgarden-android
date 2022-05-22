package com.mindgarden.mindgarden.presentation.diarylist

import android.content.Intent
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.presentation.util.base.BaseFragment
import com.mindgarden.mindgarden.presentation.writeDiary.WriteDiaryActivity
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toGardenDateString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DiaryListFragment : BaseFragment<DiaryListViewModel, FragmentDiaryListBinding>(R.layout.fragment_diary_list) {
    override val viewModel: DiaryListViewModel by viewModels()
    private lateinit var diaryListAdapter : DiaryListAdapter

    override fun setViewModel() {
        binding.vm = viewModel

        diaryListAdapter = DiaryListAdapter({
            val intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }, { diary ->
            deleteDialog(diary)
        })

        // Swipe
        val swipeHelperCallBack = SwipeHelperCallBack().apply { setClamp(200f) }
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallBack)
//        itemTouchHelper.attachToRecyclerView(binding.rvDiaryList)
//
        binding.rvDiaryList.apply {
            adapter = diaryListAdapter

//            setOnTouchListener { _, _ ->
//                swipeHelperCallBack.removePreviousClamp(this)
//                false
//            }
        }


        val btnWrite : Button = binding.btnLoad
        btnWrite.setOnClickListener {
//            val intent : Intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
//            startActivity(intent)
            viewModel.loadDiaryList(now().toGardenDateString(), false)
        }
    }

    override fun observeData() {

    }

    private fun deleteDialog(diary: Diary) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Delete selected diary?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
//                viewModel.deleteDiary(diary)
            }
        builder.show()
    }
}