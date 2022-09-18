package com.hygge.hygge.shelter_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.hygge.hygge.R
import kotlinx.android.synthetic.main.shelter_detail.*

class ShelterDetailActivity : AppCompatActivity() {

    private val imageList = mutableListOf<Int>().apply {
/*        add(R.drawable.download_1)
        add(R.drawable.download_2)
        add(R.drawable.download_3)
        add(R.drawable.download_4)*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainInitViewPager2()

    }

    private fun mainInitViewPager2(){
        content_text.text = "갈현 청소년 쉼터 쉼쉼"
        content_text_info.text = "갈현청소년센터는 전국 최초의 청소년문화의집과 여자청소년일시쉼터가 공존하는 청소년복합시설입니다."
        viewPager.apply {
            clipToPadding= false
            clipChildren= false
            offscreenPageLimit = 1
            adapter = ViewPager2Adepter(this, imageList)
        }
        viewPager.setPageTransformer(MarginPageTransformer(100))
        viewPager.setPadding(100,0,100,0)
        mainViewChangeEvent()
    }

    private fun mainViewChangeEvent(){
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
            }
        })
    }
}