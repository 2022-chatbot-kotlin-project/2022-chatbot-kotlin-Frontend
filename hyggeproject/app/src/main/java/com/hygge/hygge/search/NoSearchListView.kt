package com.hygge.hygge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NoSearchListView : AppCompatActivity() {
    val daysArray = arrayOf(1, 2, 3)
    var daysVal = daysArray.get(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_result)

        var days = findViewById<Button>(R.id.result_days)

        // days.text = "안녕".toString();

        if (daysVal == daysArray.get(0)) {
            days.text = "1일~7일"
        } else if(daysVal == daysArray.get(1)) {
            days.text = "3개월~6개월"
        } else {
            days.text = "2년"
        }
    }
}