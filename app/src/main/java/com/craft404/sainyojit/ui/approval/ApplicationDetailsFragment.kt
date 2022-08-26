package com.craft404.sainyojit.ui.approval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentApplicationDetailsBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment

class ApplicationDetailsFragment : CoreSainyojitFragment() {
    private lateinit var binding: FragmentApplicationDetailsBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[ApprovalViewModel::class.java]
    }

    companion object {
        fun newInstance(): ApplicationDetailsFragment {
            return ApplicationDetailsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_application_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
        viewModel.fetchApplicationDetails()
    }

    override fun initUI() {
        binding.verify.setOnClickListener{
            viewModel.approveTicket()
        }
    }

    override fun addObservers() {
        viewModel.application.observe(requireActivity()) {
            with(binding){
                application = it
                status.text = it.status.toString()
                executePendingBindings()
            }
        }
    }
}