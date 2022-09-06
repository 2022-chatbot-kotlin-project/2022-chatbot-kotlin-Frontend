package com.hygge.hygge.faq

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.hygge.hygge.R

class FAQActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faq_list_view)

        frameLayout = findViewById(R.id.frameLayout)

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setText("기타"))

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.view.setBackgroundColor(Color.TRANSPARENT)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("TAG", "${tab!!.position}")
                when(tab!!.position) {
                    0 -> frameLayout.setBackgroundColor(Color.parseColor("#FFBBBB"))
                    1 -> frameLayout.setBackgroundColor(Color.parseColor("#BBFFBB"))
                    2 -> frameLayout.setBackgroundColor(Color.parseColor("#BBBBFF"))
                }
            }
        })
    }
}