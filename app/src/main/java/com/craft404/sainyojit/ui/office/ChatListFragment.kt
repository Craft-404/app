package com.craft404.sainyojit.ui.office

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentSwipeRecyclerBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment

class ChatListFragment : CoreSainyojitFragment() {
    private lateinit var binding: FragmentSwipeRecyclerBinding

    companion object {
        fun newInstance(): ChatListFragment {
            return ChatListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe_recycler, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
    }

    override fun initUI() {
//        TODO("Not yet implemented")
    }

    override fun addObservers() {
//        TODO("Not yet implemented")
    }
}