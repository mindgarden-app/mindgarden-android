package com.mindgarden.mindgarden.ui.diarylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryListFragment : Fragment() {

    private val diaryListViewModel: DiaryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_diary_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        diaryListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}