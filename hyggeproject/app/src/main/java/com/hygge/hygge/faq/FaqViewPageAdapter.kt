package com.hygge.hygge.faq

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hygge.hygge.faq.fragment.FragmentAdmission
import com.hygge.hygge.faq.fragment.FragmentEtc
import com.hygge.hygge.faq.fragment.FragmentLife


class FaqViewPageAdapter(fragmentActivity: FragmentActivity): FaqFragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentAdmission()
            1 -> FragmentLife()
            else -> FragmentEtc()
        }
    }
}