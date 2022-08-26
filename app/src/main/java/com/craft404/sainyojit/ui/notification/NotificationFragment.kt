package com.craft404.sainyojit.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentSwipeRecyclerBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment

class NotificationFragment : CoreSainyojitFragment() {
    private lateinit var binding: FragmentSwipeRecyclerBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[NotificationViewModel::class.java]
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
        binding.lifecycleOwner = this
    }

    override fun addObservers() {

    }
}