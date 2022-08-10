package com.hygge.hygge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.hygge.hygge.shelter_list.Shelter
import com.hygge.hygge.shelter_list.ShelterListCustomAdapter

class MainActivity : AppCompatActivity() {

    val shelterList = arrayListOf<Shelter>(
        Shelter("쉼터1", "안녕하숑", "tmp1"),
        Shelter("쉼터2", "반가워요", "tmp_image")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shelter_list_view)

        val customAdapter = ShelterListCustomAdapter(this, shelterList)
        val listView = findViewById<ListView>(R.id.shelter_list_view)
        listView.adapter = customAdapter
    }
}