package com.hygge.hygge.map

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.SeekBar
import com.hygge.hygge.R
import kotlinx.android.synthetic.main.search_filter.*

class CustomDialog (context: Context) {
    private val dialog = Dialog(context)

    fun showDialog()
    {
        dialog.setContentView(R.layout.search_filter)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(progress == 0) {
                    dialog.termFilter.text = "24시간~7일"
                }
                else if(progress == 1){
                    dialog.termFilter.text = "3개월~6개월"
                }
                else if(progress == 2){
                    dialog.termFilter.text = "2년"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if(seekBar!!.progress == 0) {
                    dialog.termFilter.text = "24시간~7일"
                }
                else if(seekBar!!.progress == 1){
                    dialog.termFilter.text = "3개월~6개월"
                }
                else if(seekBar!!.progress== 2 ){
                    dialog.termFilter.text = "2년"
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if(seekBar!!.progress == 0) {
                    dialog.termFilter.text = "24시간~7일"
                }
                else if(seekBar!!.progress == 1){
                    dialog.termFilter.text = "3개월~6개월"
                }
                else if(seekBar!!.progress== 2 ){
                    dialog.termFilter.text = "2년"
                }
            }
        })
        dialog.show()
        dialog.button_search.setOnClickListener {
            dialog.dismiss()
        }

    }
}