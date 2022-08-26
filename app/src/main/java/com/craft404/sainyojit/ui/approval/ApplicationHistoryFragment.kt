package com.craft404.sainyojit.ui.approval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentSwipeRecyclerBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.craft404.sainyojit.ui.dashboard.TicketAdapter

class ApplicationHistoryFragment : CoreSainyojitFragment() {
    companion object {
        fun newInstance(): ApplicationHistoryFragment {
            return ApplicationHistoryFragment()
        }
    }

    private lateinit var binding: FragmentSwipeRecyclerBinding
    private val adapter = TicketAdapter()

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[ApprovalViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe_recycler, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
        viewModel.fetchTickets()
    }

    override fun initUI() {
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.fetchTickets() }

    }

    override fun addObservers() {
        viewModel.ticketsResult.observe(requireActivity()) {
            adapter.submitList(it)
        }
    }

}
