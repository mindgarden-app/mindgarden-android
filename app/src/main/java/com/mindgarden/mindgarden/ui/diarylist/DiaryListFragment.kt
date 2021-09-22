package com.mindgarden.mindgarden.ui.diarylist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.model.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentDiaryBinding
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.ui.diary.DiaryViewModel
import com.mindgarden.mindgarden.ui.util.base.BaseFragment
import com.mindgarden.mindgarden.ui.writeDiary.WriteDiaryActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class DiaryListFragment : BaseFragment<DiaryListViewModel, FragmentDiaryListBinding>(R.layout.fragment_diary_list) {
    override val viewModel: DiaryListViewModel by viewModels()
    //private lateinit var diaryListAdapter : DiaryListAdapter

    override fun setViewModel() {
        binding.vm = viewModel

        var diaryListAdapter = DiaryListAdapter({ diary ->
            val intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }, { diary ->
            deleteDialog(diary)
        })

        binding.rvDiaryList.apply {
            adapter = diaryListAdapter
        }

        viewModel.getAll().subscribe(
            {
                it -> it?.let { diaryListAdapter.submitList(it) }
            }, {
                Log.e("DiaryListViewModel", "Error : $it")
            }
        )

        val btnWrite : Button = binding.btnWrite
        btnWrite.setOnClickListener {
            val intent : Intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }
    }

    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentDiaryListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_list, container, false)

        diaryListAdapter = DiaryListAdapter({ diary ->
            val intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }, { diary ->
            deleteDialog(diary)
        })

        //Swipe
        /*
        val swipeHelperCallBack = SwipeHelperCallBack().apply { setClamp(200f) }
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvDiaryList)

        binding.rvDiaryList.apply {
            adapter = diaryListAdapter

            setOnTouchListener { _, _ ->
                swipeHelperCallBack.removePreviousClamp(this)
                false
            }
        }
        */

        /*diaryListViewModel.getAll().observe(viewLifecycleOwner, Observer { it ->
            it?.let { diaryListAdapter.submitList(it) }
        })*/

        viewModel.getDiaries().subscribe() {
                it -> it?.let { diaryListAdapter.submitList(it) }
        }

        val btnWrite : Button = binding.btnWrite
        btnWrite.setOnClickListener {
            val intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
    */

    private fun deleteDialog(diary: Diary) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Delete selected diary?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                viewModel.deleteDiary(diary)
            }
        builder.show()
    }
}