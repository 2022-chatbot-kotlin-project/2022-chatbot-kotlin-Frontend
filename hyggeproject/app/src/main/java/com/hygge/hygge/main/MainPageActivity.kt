package com.hygge.hygge.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.hygge.hygge.R
import com.hygge.hygge.shelter_list.Shelter
import com.hygge.hygge.shelter_list.ShelterListCustomAdapter

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}