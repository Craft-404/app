package com.craft404.sainyojit.ui.task

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentTaskBinding
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textview.MaterialTextView
import java.util.*

class TaskFragment : CoreSainyojitFragment() {
    private lateinit var binding: FragmentTaskBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[TaskViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
    }

    override fun initUI() {
        binding.handler = this
        val list = arrayListOf<Date>(
            Date(System.currentTimeMillis()),
            Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24),
            Date(System.currentTimeMillis() + 2000 * 60 * 60 * 24),
            Date(System.currentTimeMillis() + 3000 * 60 * 60 * 24),
            Date(System.currentTimeMillis() + 4000 * 60 * 60 * 24),
            Date(System.currentTimeMillis() + 5000 * 60 * 60 * 24),
            Date(System.currentTimeMillis() + 6000 * 60 * 60 * 24),
        )
        binding.apply {
            viewPager2.adapter = createViewPagerAdapter(list)
            viewPager2.offscreenPageLimit = 4
            TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
                val calendar = Calendar.getInstance().apply { time = list[position] }
                val customView = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER
                    //get weekday name
                    val dayOfWeekName = when (calendar.get(Calendar.DAY_OF_WEEK)) {
                        Calendar.MONDAY -> "M"
                        Calendar.TUESDAY -> "T"
                        Calendar.WEDNESDAY -> "W"
                        Calendar.THURSDAY -> "T"
                        Calendar.FRIDAY -> "F"
                        Calendar.SATURDAY -> "S"
                        Calendar.SUNDAY -> "S"
                        else -> "Unknown"
                    }
                    addView(MaterialTextView(requireContext()).apply {
                        width = ViewGroup.LayoutParams.MATCH_PARENT
                        gravity = Gravity.CENTER
                        text = dayOfWeekName
                        setTextAppearance(com.google.android.material.R.style.TextAppearance_MaterialComponents_Headline6)
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                        textSize = 16f
                    })
                    addView(MaterialTextView(requireContext()).apply {
                        width = ViewGroup.LayoutParams.MATCH_PARENT
                        gravity = Gravity.CENTER
                        text = calendar.get(Calendar.DAY_OF_MONTH).toString()
                        setTextAppearance(com.google.android.material.R.style.TextAppearance_MaterialComponents_Headline6)
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        textSize = 16f
                        setPadding(0, 4, 0, 0)
                    })
                }
                tab.customView = customView
            }.attach()
        }
    }

    override fun addObservers() {

    }

    private fun createViewPagerAdapter(items: List<Date>): RecyclerView.Adapter<*> {
        return object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): TaskCalendarFragment {
                val item = items[position]
                return TaskCalendarFragment.newInstance(item)
            }

            override fun getItemCount(): Int = items.size
        }
    }

    fun addTask(v: View) {
        findNavController().navigate(TaskFragmentDirections.actionNavigationTaskToAssignTaskFragment())
    }
}