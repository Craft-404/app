package com.craft404.sainyojit.repository.model

import com.craft404.sainyojit.repository.entity.TicketEntity

sealed class ApprovalRecyclerItem {
    data class ApprovalItem(val approval: TicketEntity) : ApprovalRecyclerItem()
    data class HeaderItem(val header: String) : ApprovalRecyclerItem()
}