package com.mindgarden.mindgarden.ui.diarylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mindgarden.mindgarden.R

// TODO : BaseFragment 상속받아 databinding 적용, DiaryViewModelFactory에서 viewmodel 생성하기
class DiaryListFragment : Fragment() {

    private lateinit var diaryListViewModel: DiaryListViewModel

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
}