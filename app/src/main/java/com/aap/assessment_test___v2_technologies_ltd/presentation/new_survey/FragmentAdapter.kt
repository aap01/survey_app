package com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(private val fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    private var list: List<QuestionFragment> = emptyList()
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    fun setFragmentList(list: List<QuestionFragment>) {
        this.list = list
        notifyDataSetChanged()
    }
}