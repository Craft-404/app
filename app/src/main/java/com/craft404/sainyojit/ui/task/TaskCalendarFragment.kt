package com.craft404.sainyojit.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentSwipeRecyclerBinding
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.craft404.sainyojit.ui.dialog.TicketDialog
import com.craft404.sainyojit.util.ItemClickListener
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.showAppropriateMsg
import java.util.*

class TaskCalendarFragment : CoreSainyojitFragment(), ItemClickListener {
    private lateinit var binding: FragmentSwipeRecyclerBinding
    private var calendar: Calendar = Calendar.getInstance()
    val adapter = TaskCalendarAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this)[TaskViewModel::class.java]
    }

    companion object {
        private const val ARG_DATE = "ARG_DATE"
        fun newInstance(date: Date): TaskCalendarFragment {
            val args = Bundle()
            args.putLong(ARG_DATE, date.time)
            val fragment = TaskCalendarFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe_recycler, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar.time = arguments?.getLong(ARG_DATE)?.let { Date(it) } ?: Date()
        initUI()
        addObservers()
        adapter.setListener(this)
        viewModel.fetchTasks(calendar, calendar.before(Calendar.getInstance()))
    }

    override fun initUI() {
        binding.recyclerView.adapter = adapter
    }

    override fun addObservers() {
        viewModel.taskResult.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it is Result.Loading
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.data.tasks)
                }
                is Result.Error -> {
                    it.exception.showAppropriateMsg()
                }
                is Result.Loading -> {}
            }
        }
    }

    override fun <T> onItemClick(position: Int, item: T) {
        val dialog = TicketDialog.newInstance(item as TicketEntity)
        dialog.show(childFragmentManager, "TicketDialog")
    }
}
