package com.craft404.sainyojit.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentDashboardBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.craft404.sainyojit.util.Result

class DashboardFragment : CoreSainyojitFragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[DashboardViewModel::class.java]
    }
    private val approvalAdapter = TicketAdapter()
    private val taskAdapter = TicketAdapter()
    private val overdueAdapter = TicketAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
    }

    override fun initUI() {
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchDashboard()
            }
            rvApprovals.adapter = approvalAdapter
            rvTasks.adapter = taskAdapter
            rvOverdue.adapter = overdueAdapter
        }
    }

    override fun addObservers() {
        viewModel.result.observe(viewLifecycleOwner) {
            with(binding) {
                swipeRefreshLayout.isRefreshing = it is Result.Loading
                shimmerAnnouncements.isVisible = it is Result.Loading
                shimmerApprovals.isVisible = it is Result.Loading
                shimmerOverdue.isVisible = it is Result.Loading
                shimmerTasks.isVisible = it is Result.Loading
                if(noAnnouncements.isInflated)
                    noAnnouncements.viewStub?.isVisible = (it is Result.Loading).not()
                if(noTasks.isInflated)
                    noTasks.viewStub?.isVisible = (it is Result.Loading).not()
                if(noApprovals.isInflated)
                    noApprovals.viewStub?.isVisible = (it is Result.Loading).not()
                rvApprovals.isVisible = it is Result.Success
                rvOverdue.isVisible = it is Result.Success
                rvTasks.isVisible = it is Result.Success
            }
            when (it) {
                is Result.Success -> {
                    Log.d(
                        "DashboardFragment.kt",
                        "YASH => addObservers:66 ${it.data.announcements.joinToString(separator = "\n•") { announcementModel -> announcementModel.name }}"
                    )
                    with(binding) {
                        rvOverdue.isVisible = it.data.overdue.isNotEmpty()
                        rvApprovals.isVisible = it.data.approvals.isNotEmpty()
                        rvTasks.isVisible = it.data.tasks.isNotEmpty()
                        announcementCard.isVisible = it.data.announcements.isNotEmpty()
                    }
                    if (it.data.approvals.isEmpty())
                        binding.noApprovals.viewStub?.inflate().also { view ->
                            view?.visibility = View.VISIBLE
                        }
                    else {
                        binding.noApprovals.viewStub?.visibility = View.GONE
                        approvalAdapter.submitList(it.data.approvals)
                    }
                    if (it.data.tasks.isEmpty())
                        binding.noTasks.viewStub?.inflate().also { view ->
                            view?.visibility = View.VISIBLE
                        }
                    else {
                        binding.noTasks.viewStub?.visibility = View.GONE
                        taskAdapter.submitList(it.data.tasks)
                    }

                    binding.groupOverdue.isVisible = it.data.overdue.isNotEmpty()
                    overdueAdapter.submitList(it.data.overdue)

                    if (it.data.announcements.isEmpty()) {
                        Log.d("DashboardFragment.kt", "YASH => addObservers:98 ")
                        binding.noAnnouncements.viewStub?.inflate().also { view ->
                            view?.visibility = View.VISIBLE
                        }
                    } else {
                        Log.d("DashboardFragment.kt", "YASH => addObservers:103 ")
                        binding.noAnnouncements.viewStub?.visibility = View.GONE
                        binding.announcements.text = it.data.announcements.joinToString(separator = "\n•") { announcementModel -> announcementModel.name }
                    }
                    binding.swipeRefreshLayout.isRefreshing = false
                    hideLoading()
                }
                is Result.Loading -> {
                    showLoading()
                }
                is Result.Error -> {
                    hideLoading()
                }
            }
            binding.executePendingBindings()
        }
    }
}