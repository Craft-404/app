package com.craft404.sainyojit.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.craft404.sainyojit.databinding.ItemCalendarTaskBinding
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.repository.model.DiffUtilCallback
import com.craft404.sainyojit.util.ItemClickListener

class TaskCalendarAdapter : ListAdapter<TicketEntity, TaskCalendarAdapter.TaskViewHolder>(DiffUtilCallback) {
    private lateinit var listener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemCalendarTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onItemClick(position, item) }
    }

    inner class TaskViewHolder(private val binding: ItemCalendarTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketEntity) {
            binding.ticketEntity = item
            binding.executePendingBindings()
        }
    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }
}