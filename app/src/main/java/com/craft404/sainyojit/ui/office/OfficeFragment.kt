package com.craft404.sainyojit.ui.office

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentOfficeBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.google.android.material.tabs.TabLayoutMediator

class OfficeFragment : CoreSainyojitFragment() {
    private lateinit var binding: FragmentOfficeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_office, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
    }


    override fun initUI() {
        with(binding) {
            viewPager.adapter = createViewPagerAdapter()
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Chat"
                    1 -> "Video"
                    2 -> "Doc Upload"
                    else -> "Template"
                }
            }.attach()
        }
    }

    private fun createViewPagerAdapter(): RecyclerView.Adapter<*> {
        return object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): CoreSainyojitFragment {
                return when (position) {
                    0 -> ChatListFragment.newInstance()
                    1 -> VideoCallFragment.newInstance()
                    2 -> DocUploadFragment.newInstance()
                    else -> TemplateFragment.newInstance()
                }
            }

            override fun getItemCount(): Int = 4
        }
    }

    override fun addObservers() {
    }
}