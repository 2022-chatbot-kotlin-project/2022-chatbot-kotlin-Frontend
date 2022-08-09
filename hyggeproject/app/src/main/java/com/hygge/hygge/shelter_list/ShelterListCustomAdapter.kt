package com.hygge.hygge.shelter_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.hygge.hygge.R

class ShelterListCustomAdapter(val context: Context, val shelterList: ArrayList<Shelter>) :  BaseAdapter() {
    override fun getCount(): Int {    //ListView에 속한 아이템 전체 수를 반환함
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {   //해당 위치의 item id를 반환
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {   //해당 위치의 아이템을 반환
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {   //View와 데이터를 연결
        val view: View = LayoutInflater.from(context).inflate(R.layout.shelter_custom_item, null)

        val shImage = view.findViewById<ImageView>(R.id.shelter_image)
        val shTitle = view.findViewById<TextView>(R.id.shelter_title)
        val shDescription = view.findViewById<TextView>(R.id.shelter_description)

        val shelter = shelterList[p0]
        val photoId = context.resources.getIdentifier()

    }
}