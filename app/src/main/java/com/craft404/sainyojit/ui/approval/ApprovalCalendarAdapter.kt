package com.craft404.sainyojit.ui.approval

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ItemCalendarApprovalBinding
import com.craft404.sainyojit.databinding.ItemCalendarTaskBinding
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.repository.model.ApprovalRecyclerItem
import com.craft404.sainyojit.util.ItemClickListener

const val TYPE_HEADER = 0
const val TYPE_ITEM = 1

class ApprovalCalendarAdapter : ListAdapter<ApprovalRecyclerItem, RecyclerView.ViewHolder>(DiffUtilCallback) {
    private var listener: ItemClickListener? = null

    object DiffUtilCallback : DiffUtil.ItemCallback<ApprovalRecyclerItem>() {
        override fun areItemsTheSame(oldItem: ApprovalRecyclerItem, newItem: ApprovalRecyclerItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ApprovalRecyclerItem, newItem: ApprovalRecyclerItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> ApprovalCalendarHeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    android.R.layout.simple_list_item_1,
                    parent,
                    false
                )
            )
            TYPE_ITEM -> ApprovalCalendarItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_calendar_approval,
                    parent,
                    false
                )
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    inner class ApprovalCalendarHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) {
            itemView.findViewById<android.widget.TextView>(android.R.id.text1).text = item
        }
    }

    inner class ApprovalCalendarItemViewHolder(private val binding: ItemCalendarApprovalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketEntity) {
            binding.ticketEntity = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ApprovalCalendarHeaderViewHolder -> {
                holder.bind((getItem(position) as ApprovalRecyclerItem.HeaderItem).header)
            }
            is ApprovalCalendarItemViewHolder -> {
                val ticketEntity = (getItem(position) as ApprovalRecyclerItem.ApprovalItem).approval
                holder.bind(ticketEntity)
                holder.itemView.setOnClickListener { listener?.onItemClick(position, ticketEntity) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ApprovalRecyclerItem.HeaderItem -> TYPE_HEADER
            else -> TYPE_ITEM
        }
    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }
}
