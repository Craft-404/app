package com.craft404.sainyojit.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.databinding.DialogEmployeeListBinding
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.util.ItemClickListener
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.showAppropriateMsg
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmployeeListDialog : BottomSheetDialogFragment(), ItemClickListener {
    private lateinit var binding: DialogEmployeeListBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[EmployeeListViewModel::class.java]
    }
    private val adapter = EmployeeAdapter()
    private lateinit var listener: EmployeeListDialogListener

    companion object {
        fun newInstance(): EmployeeListDialog {
            return EmployeeListDialog()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        addObservers()
    }

    private fun initUi() {
        binding.viewModel = viewModel
        adapter.setOnClickListener(this)
        binding.editQuery.requestFocus()
        binding.recyclerView.adapter = adapter
    }

    private fun addObservers() {
        viewModel.result.observe(viewLifecycleOwner) {
            binding.shimmerEmployee.isVisible = it is Result.Loading
            when (it) {
                is Result.Loading -> {}
                is Result.Success -> {
                    binding.noResults.isVisible = it.data.isEmpty()
                    adapter.submitList(it.data)
                }
                is Result.Error -> it.exception.showAppropriateMsg()
            }
        }
    }

    fun setEmployeeListDialogListener(listener: EmployeeListDialogListener) {
        this.listener = listener
    }

    interface EmployeeListDialogListener {
        fun onEmployeeSelected(employee: EmployeeModel)
    }

    override fun <T> onItemClick(position: Int, item: T) {
        listener.onEmployeeSelected(item as EmployeeModel)
        dismiss()
    }
}

