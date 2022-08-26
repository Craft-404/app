package com.craft404.sainyojit.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentAssignTaskBinding
import com.craft404.sainyojit.repository.entity.TicketCategory
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.craft404.sainyojit.ui.dialog.EmployeeListDialog
import com.craft404.sainyojit.util.*

class AssignTaskFragment : CoreSainyojitFragment(), EmployeeListDialog.EmployeeListDialogListener {
    private lateinit var binding: FragmentAssignTaskBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[TaskViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_assign_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
    }


    override fun initUI() {
        with(binding) {
            lifecycleOwner = this@AssignTaskFragment
            editStart.setDateTime(viewModel.startDate)
            val priorities = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                requireContext().resources.getStringArray(R.array.priorities)
            )
            editPriority.setAdapter(priorities)
            layoutPriority.startIconDrawable = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_priority_medium
            )
            layoutAssignee.setEndIconOnClickListener {
                //open employee list dialog
                val dialog = EmployeeListDialog.newInstance()
                dialog.setEmployeeListDialogListener(this@AssignTaskFragment)
                dialog.show(childFragmentManager, "EmployeeListDialog")
            }
            editPriority.setOnItemClickListener { _, _, position, _ ->
                layoutPriority.startIconDrawable = ContextCompat.getDrawable(
                    requireContext(),
                    when (position + 1) {
                        1 -> R.drawable.ic_priority_lowest
                        2 -> R.drawable.ic_priority_low
                        3 -> R.drawable.ic_priority_medium
                        4 -> R.drawable.ic_priority_high
                        5 -> R.drawable.ic_priority_highest
                        else -> R.drawable.ic_priority_medium
                    }
                )
            }
            editTitle.doAfterTextChanged {
                layoutTitle.error =
                    if (it?.isEmpty() == true)
                        "Cannot be empty!"
                    else null
            }
            editDescription.doAfterTextChanged {
                layoutTitle.error =
                    if (it?.isEmpty() == true)
                        "Cannot be empty!"
                    else null
            }
            layoutStart.setEndIconOnClickListener {
                requireContext().showDateTimePicker {
                    editStart.setDateTime(it)
                    viewModel.startDate = it
                }
            }
            layoutDue.setEndIconOnClickListener {
                requireContext().showDateTimePicker {
                    editDue.setDateTime(it)
                    viewModel.dueDate = it
                }
            }
            submit.setOnClickListener {
                if (editTitle.text.isNullOrEmpty())
                    showToast("Title cannot be empty!")
                else if (editDescription.text.isNullOrEmpty())
                    showToast("Description cannot be empty!")
                else if (viewModel.dueDate == null)
                    showToast("Due date cannot be empty!")
                else if (viewModel.startDate.after(viewModel.dueDate!!))
                    showToast("Start date cannot be after due date!")
                else try {
                    viewModel.postTicket(
                        title = editTitle.text.toString(),
                        description = editDescription.text.toString(),
                        priority = priorities.getPosition(editPriority.text.toString()).plus(1),
                        category = TicketCategory.TASK
                    )
                } catch (e: Exception) {
                    e.showAppropriateMsg()
                    Log.e("AssignTaskFragment", "YASH", e)
                }
            }
        }
    }

    override fun addObservers() {
        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> showLoading()
                is Result.Success -> {
                    showToast("Task created successfully!")
                    findNavController().popBackStack()
                }
                is Result.Error -> showToast(it.exception.message)
            }
        }
    }

    override fun onEmployeeSelected(employee: EmployeeModel) {
        viewModel.assignee = employee
        binding.editAssignee.setText(employee.name)
    }
}