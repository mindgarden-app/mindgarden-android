package com.mindgarden.mindgarden.presentation.diarylist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.data.db.entity.Diary
import com.mindgarden.mindgarden.databinding.FragmentDiaryListBinding
import com.mindgarden.mindgarden.presentation.util.base.BaseFragment
import com.mindgarden.mindgarden.presentation.util.base.UIState
import com.mindgarden.mindgarden.presentation.writeDiary.WriteDiaryActivity
import com.mindgarden.mindgarden.util.ext.now
import com.mindgarden.mindgarden.util.ext.toGardenDate
import com.mindgarden.mindgarden.util.ext.toGardenDateString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

        // 일기 개별 삭제 - 스와이프
        val swipeHelperCallBack = SwipeHelperCallBack().apply { setClamp(170f) }
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvDiaryList)

        binding.rvDiaryList.apply {
            diaryListAdapter = DiaryListAdapter({
                val intent = Intent(requireActivity(), WriteDiaryActivity::class.java)
                startActivity(intent)
            }, { diary ->
                deleteDialog(diary)
            })

            adapter = diaryListAdapter

            // 일기 개별 삭제 - 스와이프
            setOnTouchListener { _, _ ->
                swipeHelperCallBack.removePreviousClamp(this)
                false
            }

            // 일기 목록 조회
            /*
            val btnWrite : Button = binding.btnLoad
            btnWrite.setOnClickListener {
                viewModel.loadDiaryList(now().toGardenDateString(), false)
            }
            */

            /*과거 : 일기 목록 조회
            viewModel.getAll().subscribe(
                {
                        it -> it?.let { diaryListAdapter.submitList(it) }
                }, {
                    Log.e("DiaryListViewModel", "Error : $it")
                }
            )
            */

            // 일기 목록 조회
            //observeData()




            //binding.tvDate.text = now().toGardenDateString()
            binding.tvDate.text = today.format(DateTimeFormatter.ofPattern("yyyy-MM"))

            // 일기 목록 조회
            observeDiaryList(binding.tvDate.text.toString(), false)

            binding.btnLeft.setOnClickListener {
                today = today.minusMonths(1)
                binding.tvDate.text = today.format(DateTimeFormatter.ofPattern("yyyy-MM"))
                //binding.tvDate.text = now().toGardenDate().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"))
                observeDiaryList(binding.tvDate.text.toString(), false)
            }

            binding.btnRight.setOnClickListener {
                today = today.plusMonths(1)
                binding.tvDate.text = today.format(DateTimeFormatter.ofPattern("yyyy-MM"))
                //binding.tvDate.text = now().toGardenDate().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"))
                observeDiaryList(binding.tvDate.text.toString(), false)
            }

            var isasc = true;
            binding.imgSort.setOnClickListener {
                observeDiaryList(binding.tvDate.text.toString(), isasc)
                isasc = !isasc
            }
        }
    }

    /*
    override fun observeData() {
        viewModel.loadDiaryList(now().toGardenDateString(), false)
    }
    */

    private fun observeDiaryList(date : String, asc : Boolean) {
        viewModel.loadDiaryList(date, asc)

        //binding.llListZero.visibility = View.VISIBLE
        //binding.llListZero.visibility = View.GONE
        //Log.e("테스트 : ", diaryListAdapter.currentList.size.toString())
        //Log.e("테스트 : ", diaryListAdapter.itemCount.toString())

        viewModel.state.asLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UIState.Success -> {
                    if (it.data.size == 0) {
                        binding.llListZero.visibility = View.VISIBLE
                    } else {
                        binding.llListZero.visibility = View.GONE
                        diaryListAdapter.submitList(it.data)
                        diaryListAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        /*
        if (diaryListAdapter.itemCount == 0) {
            binding.llListZero.visibility = View.VISIBLE
        } else {
            binding.llListZero.visibility = View.GONE
            diaryListAdapter.notifyDataSetChanged()
        }
        */
    }

    private fun deleteDialog(diary: Diary) {
        /*
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("일기 삭제").setMessage("삭제하시겠습니까?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("삭제") { _, _ ->
                viewModel.deleteDiary(diary.idx)
                diaryListAdapter.notifyDataSetChanged()
            }
        val dlg: AlertDialog = builder.show()
        messageModi(dlg)
        buttonModi(dlg)
        dlg.show()
        */
        val dlg = LessonDeleteDialog(this@DiaryListFragment.context!!)
        dlg.listener = object : LessonDeleteDialog.LessonDeleteDialogClickedListener {
            override fun onDeleteClicked() {
                viewModel.deleteDiary(diary.idx)
                diaryListAdapter.notifyDataSetChanged()
            }
        }
        dlg.start()
    }
    /*
    fun messageModi(dlg : AlertDialog) {
        var messageText: TextView? = dlg.findViewById(android.R.id.message)
        messageText!!.gravity = Gravity.CENTER
        //messageText!!.typeface = ResourcesCompat.getFont(holder.itemView.context, R.font.notosanscjkr_regular)
        messageText!!.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
    }

    fun buttonModi(dlg : AlertDialog) {
        val btnNegative = dlg.getButton(AlertDialog.BUTTON_NEGATIVE);
        val btnPositive = dlg.getButton(AlertDialog.BUTTON_POSITIVE);

        btnNegative.gravity = Gravity.CENTER
        //btnNegative.typeface = ResourcesCompat.getFont(holder.itemView.context, R.font.notosanscjkr_medium)
        btnNegative.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)

        btnPositive.gravity = Gravity.CENTER
        //btnPositive.typeface = ResourcesCompat.getFont(holder.itemView.context, R.font.notosanscjkr_medium)
        btnPositive.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)

        val layoutParams : LinearLayout.LayoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 10f;
        btnNegative.setLayoutParams(layoutParams);
        btnPositive.setLayoutParams(layoutParams);
    }
    */
}