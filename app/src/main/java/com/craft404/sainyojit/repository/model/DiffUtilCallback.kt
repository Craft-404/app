package com.craft404.sainyojit.repository.model

import androidx.recyclerview.widget.DiffUtil
import com.craft404.sainyojit.repository.entity.TicketEntity

/**
 * @author yashkasera
 * Created 25/08/22 at 6:36 PM
 */
object DiffUtilCallback : DiffUtil.ItemCallback<TicketEntity>() {
    override fun areItemsTheSame(oldItem: TicketEntity, newItem: TicketEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketEntity, newItem: TicketEntity): Boolean {
        return oldItem == newItem
    }
}