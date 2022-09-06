package com.hygge.hygge.faq

import android.os.Bundle
import com.hygge.hygge.databinding.FaqListViewBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator


class FaqActivity : AppCompatActivity() {
    private val binding: FaqListViewBinding by lazy { FaqListViewBinding.inflate(layoutInflater) }

    private val tabTextList = listOf<String>("입소", "생활", "기타")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager.adapter = FaqViewPageAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()
    }
}