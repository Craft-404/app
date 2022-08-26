package com.craft404.sainyojit.ui.approval

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ActivityApproveApplicationBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.google.android.material.tabs.TabLayoutMediator

class ApproveApplicationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApproveApplicationBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[ApprovalViewModel::class.java]
    }
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_approve_application)
        intent.getStringExtra(APPLICATION_ID)?.let {
            viewModel.applicationId = it
        } ?: run { finish() }
        intent.getStringExtra(TICKET_ID)?.let {
            viewModel.ticketId = it
        } ?: run { finish() }
        progressDialog = ProgressDialog(this)
        initUI()
        addObserver()
    }

    private fun addObserver() {
        viewModel.isProcessing.observe(this) {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.hide()
            }
        }
    }

    private fun initUI() {
        with(binding) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.title = "Approve Application"
            toolbar.setNavigationOnClickListener {
                finish()
            }
            viewPager2.adapter = createViewPagerAdapter()
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = when (position) {
                    0 -> "Details"
                    1 -> "Documents"
                    else -> "History"
                }
            }.attach()
        }
    }

    private fun createViewPagerAdapter(): RecyclerView.Adapter<*> {
        return object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): CoreSainyojitFragment {
                return when (position) {
                    0 -> ApplicationDetailsFragment.newInstance()
                    1 -> VerifyDocumentFragment.newInstance()
                    2 -> ApplicationHistoryFragment.newInstance()
                    else -> {
                        throw IllegalArgumentException("Invalid position")
                    }
                }
            }

            override fun getItemCount(): Int = 3
        }
    }

    companion object {
        private const val APPLICATION_ID = "applicationId"
        private const val TICKET_ID = "ticketId"

        @JvmStatic
        fun start(context: Context, applicationId: String, ticketId: String) {
            val starter = Intent(context, ApproveApplicationActivity::class.java)
                .putExtra(APPLICATION_ID, applicationId)
                .putExtra(TICKET_ID, ticketId)
            context.startActivity(starter)
        }
    }
}