package com.craft404.sainyojit.util

interface ItemClickListener {
    fun <T> onItemClick(position: Int, item: T)
}

interface ItemLongClickListener {
    fun <T> onItemLongClick(position: Int, item: T)
}