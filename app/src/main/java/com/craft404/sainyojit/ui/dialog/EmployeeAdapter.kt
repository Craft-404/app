package com.craft404.sainyojit.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ItemEmployeeBinding
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.util.ItemClickListener

class EmployeeAdapter : ListAdapter<EmployeeModel, EmployeeAdapter.EmployeeViewHolder>(DiffCallback) {
    private lateinit var listener: ItemClickListener

    object DiffCallback : DiffUtil.ItemCallback<EmployeeModel>() {
        override fun areItemsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_employee,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { listener.onItemClick(position, getItem(position)) }
    }

    fun setOnClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeModel: EmployeeModel) {
            binding.employee = employeeModel
            binding.executePendingBindings()
        }
    }
}