package com.craft404.sainyojit.ui.office

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ItemVideoBinding
import com.craft404.sainyojit.repository.model.MeetingModel

class MeetingListAdapter : ListAdapter<MeetingModel, MeetingListAdapter.MeetingViewHolder>(MeetingDiffCallback) {

    private object MeetingDiffCallback : DiffUtil.ItemCallback<MeetingModel>() {
        override fun areItemsTheSame(oldItem: MeetingModel, newItem: MeetingModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MeetingModel, newItem: MeetingModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        return MeetingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_video,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MeetingViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meeting: MeetingModel) {
            binding.meeting = meeting
        }
    }
}