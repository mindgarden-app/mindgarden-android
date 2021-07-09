package com.mindgarden.mindgarden.ui.diarylist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.data.model.Diary
import com.mindgarden.mindgarden.databinding.RvItemDiaryListBinding
import com.mindgarden.mindgarden.ui.writeDiary.WriteDiaryActivity

class DiaryListFragment : Fragment() {
    //private lateinit var diaryListViewModel: DiaryListViewModel
    private val diaryListViewModel by viewModels<DiaryListViewModel> {
        DiaryListViewModelFactory(requireActivity().application) }
    private lateinit var diaryListAdapter : DiaryListAdapter

    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        diaryListViewModel =
            ViewModelProvider(this).get(DiaryListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_diary_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        diaryListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        //val root = inflater.inflate(R.layout.fragment_diary_list, container, false)

        //DiffUtil
        //val binding : FragmentDiaryListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_list, container, false)

        val binding : FragmentDiaryListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_list, container, false)

        diaryListAdapter = DiaryListAdapter({ diary ->
            val intent : Intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }, { diary ->
            deleteDialog(diary)
        })

        //Swipe
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

        /* DiffUtil
        val adapter = DiaryListRecyclerViewAdapter({ diary ->
            val intent : Intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }, { diary ->
            deleteDialog(diary)
        })
        */

        //DiffUtil
        //setRecyclerView(binding, adapter)

        //diaryListViewModel = ViewModelProvider(this).get(DiaryListViewModel::class.java)

        /*diaryListViewModel =
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(DiaryListViewModel::class.java)*/

        /*diaryListViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
                .get(DiaryListViewModel::class.java)*/

        /* DiffUtil
        diaryListViewModel.getAll().observe(viewLifecycleOwner, Observer<List<Diary>> { diaries ->
            adapter.setDiaries(diaries!!)
        })
        */

        diaryListViewModel.getAll().observe(viewLifecycleOwner, Observer { it ->
            it?.let { diaryListAdapter.submitList(it) }
        })

        val btnWrite : Button = binding.btnWrite
        btnWrite.setOnClickListener {
            val intent : Intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    /* DiffUtil
    private fun setRecyclerView(binding : FragmentDiaryListBinding, adapter : DiaryListRecyclerViewAdapter) {
        val rv : RecyclerView = binding.rvDiaryList
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv.setHasFixedSize(true)
    }
    */

    private fun deleteDialog(diary: Diary) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Delete selected diary?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                diaryListViewModel.delete(diary)
            }
        builder.show()
    }
}