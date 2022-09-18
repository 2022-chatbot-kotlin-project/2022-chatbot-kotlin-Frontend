package com.hygge.hygge.map

import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.inflate
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.hygge.hygge.databinding.SearchFilterBinding
import kotlinx.android.synthetic.main.search_filter.*
import kotlinx.coroutines.*

class SearchFilterActivity : AppCompatActivity(){
    private val TAG = "SearchFilterActivity"
    lateinit var binding : SearchFilterBinding;
    private var count = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(progress == 0) {
                    termFilter.text = "24시간~7일"
                }
                else if(progress == 1){
                    termFilter.text = "3개월~6개월"
                }
                else if(progress == 2){
                    termFilter.text = "2년"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if(seekBar!!.progress == 0){
                    termFilter.text = "1일~7일"
                }
                else if(seekBar!!.progress == 1){
                    termFilter.text = "3개월~6개월"
                }
                else if(seekBar!!.progress == 2){
                    termFilter.text = "2년"
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if(seekBar!!.progress == 0){
                    termFilter.text = "1일~7일"
                }
                else if(seekBar!!.progress == 1){
                    termFilter.text = "3개월~6개월"
                }
                else if(seekBar!!.progress == 2){
                    termFilter.text = "2년"
                }
            }
        })
//        status_1!!.setOnClickListener(this)
//        status_2!!.setOnClickListener(this)
//        status_3!!.setOnClickListener(this)
//        status_4!!.setOnClickListener(this)
//        status_5!!.setOnClickListener(this)
//        status_6!!.setOnClickListener(this)
    }

//    override fun onClick(v: View?) {
//        if(count % 2 == 0){
//        when (v?.id) {
//                R.id.status_1 -> {
//                    status_1.setBackgroundResource(R.drawable.round_button_2)
//                }
//                R.id.status_2 -> {
//                    status_2.setBackgroundResource(R.drawable.round_button_2)
//                }
//                R.id.status_3 -> {
//                    status_3.setBackgroundResource(R.drawable.round_button_2)
//                }
//                R.id.status_4 -> {
//                    status_4.setBackgroundResource(R.drawable.round_button_2)
//                }
//                R.id.status_5 -> {
//                    status_5.setBackgroundResource(R.drawable.round_button_2)
//                }
//                R.id.status_6 -> {
//                    status_6.setBackgroundResource(R.drawable.round_button_2)
//                }
//                else -> {
//                }
//            }
//        }
//        if(count % 2 != 0){
//            when (v?.id) {
//                R.id.status_1 -> {
//                    status_1.setBackgroundResource(R.drawable.round_button_3)
//                }
//                R.id.status_2 -> {
//                    status_2.setBackgroundResource(R.drawable.round_button_3)
//                }
//                R.id.status_3 -> {
//                    status_3.setBackgroundResource(R.drawable.round_button_3)
//                }
//                R.id.status_4 -> {
//                    status_4.setBackgroundResource(R.drawable.round_button_3)
//                }
//                R.id.status_5 -> {
//                    status_5.setBackgroundResource(R.drawable.round_button_3)
//                }
//                R.id.status_6 -> {
//                    status_6.setBackgroundResource(R.drawable.round_button_3)
//                }
//                else -> {
//                }
//            }
//        }
//        count++
//    } 다시 사용할 시, 클래스에 < , View.OnClickListener > <<이거 추가할것
}
