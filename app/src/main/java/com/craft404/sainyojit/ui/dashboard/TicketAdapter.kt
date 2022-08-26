package com.craft404.sainyojit.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ItemTaskBinding
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.repository.model.DiffUtilCallback

class TicketAdapter : ListAdapter<TicketEntity, TicketAdapter.TicketViewHolder>(DiffUtilCallback) {

    inner class TicketViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ticketEntity: TicketEntity) {
            binding.ticketEntity = ticketEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder =
        TicketViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_task,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}