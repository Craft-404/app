package com.craft404.sainyojit.ui.approval

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
import com.craft404.sainyojit.util.ItemClickListener
import com.craft404.sainyojit.util.showToast
import java.util.*

class ApprovalCalendarFragment : CoreSainyojitFragment(), ItemClickListener {
    private lateinit var binding: FragmentSwipeRecyclerBinding
    private var calendar: Calendar = Calendar.getInstance()
    private val adapter = ApprovalCalendarAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this)[ApprovalViewModel::class.java]
    }

    companion object {
        private const val ARG_DATE = "ARG_DATE"
        fun newInstance(date: Date): ApprovalCalendarFragment {
            val args = Bundle()
            args.putLong(ARG_DATE, date.time)
            val fragment = ApprovalCalendarFragment()
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
        viewModel.fetchApprovals(calendar, calendar.before(Calendar.getInstance()))
    }

    override fun initUI() {
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchApprovals(calendar, calendar.before(Calendar.getInstance()))
        }
    }

    override fun addObservers() {
        viewModel.isProcessing.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
        viewModel.approvalResult.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                showToast("No approvals found")
            } else {
                adapter.submitList(it)
            }
        }
    }

    override fun <T> onItemClick(position: Int, item: T) {
        (item as TicketEntity).let {
            ApproveApplicationActivity.start(
                context = requireContext(),
                applicationId = it.applicationId?.id ?: "",
                ticketId = it.id
            )
        }
    }
}