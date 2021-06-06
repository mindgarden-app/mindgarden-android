package com.mindgarden.mindgarden.ui.diarylist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.adapter.DiaryListRecyclerViewAdapter
import com.mindgarden.mindgarden.factory.DiaryListViewModelFactory
import com.mindgarden.mindgarden.model.Diary

class DiaryListFragment : Fragment() {
    //private lateinit var diaryListViewModel: DiaryListViewModel
    private val diaryListViewModel by viewModels<DiaryListViewModel> {
        DiaryListViewModelFactory(requireActivity().application) }

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
        val root = inflater.inflate(R.layout.fragment_diary_list, container, false)

        val adapter = DiaryListRecyclerViewAdapter({ diary ->
            deleteDialog(diary)
        })

        val rv : RecyclerView = root.findViewById(R.id.rv_diary_list)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv.setHasFixedSize(true)

        //diaryListViewModel = ViewModelProvider(this).get(DiaryListViewModel::class.java)

        /*diaryListViewModel =
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(DiaryListViewModel::class.java)*/

        /*diaryListViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
                .get(DiaryListViewModel::class.java)*/

        diaryListViewModel.getAll().observe(viewLifecycleOwner, Observer<List<Diary>> { diaries ->
            adapter.setContacts(diaries!!)
        })

        val btnAdd : Button = root.findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            val intent : android.content.Intent = android.content.Intent(requireActivity(), com.mindgarden.mindgarden.ui.add.AddActivity::class.java)
            startActivity(intent)
        }

        return root
    }

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