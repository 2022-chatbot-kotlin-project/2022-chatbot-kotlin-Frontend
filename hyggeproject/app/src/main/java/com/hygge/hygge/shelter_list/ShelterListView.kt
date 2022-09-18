package com.hygge.hygge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.hygge.hygge.shelter_detail.ShelterDetailActivity
import com.hygge.hygge.shelter_list.Shelter
import com.hygge.hygge.shelter_list.ShelterListCustomAdapter
import kotlinx.android.synthetic.main.shelter_list_view.*

class ShelterListView : AppCompatActivity() {

    val shelterList = arrayListOf<Shelter>(
        Shelter("쉼터1", "안녕하숑", "tmp1"),
        Shelter("쉼터2", "반가워요", "tmp_image")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("*****", "ShelterListView onCreate실행")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.shelter_list_view)

        val customAdapter = ShelterListCustomAdapter(this, shelterList)
        val listView = findViewById<ListView>(R.id.shelter_list_view)
        listView.adapter = customAdapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("*****", "setOnItemClickListener 샐행")

            // 눌린 위치에 해당하는 목록이 어떤 목록인지 가져오기
            //val clickedRoom = mRoomList[i]

            // 선택된 목록정보를 가져왔으면 이제 화면 이동
            val myIntent = Intent(this, ShelterDetailActivity::class.java)

            // 정보를 담아주기
            // 2번에서는 해당 부분 오류남. 3번하고 난 다음 여기로 다시 와야함
            //myIntent.putExtra("roomInfo", clickedRoom)

            // 화면 전환
            startActivity(myIntent)
        }
    }





}