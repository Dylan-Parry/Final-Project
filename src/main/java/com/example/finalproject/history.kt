package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_history.*
import java.util.*
import kotlin.collections.ArrayList

class history : AppCompatActivity() {
    var historyList = ArrayList<checkincard>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        getHistory()

        historyList.reverse()

        recyclerView.adapter = historyAdapter(historyList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun getHistory(){
        val sharedPreferences = getSharedPreferences("history", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", null )
        val type = object : TypeToken<ArrayList<checkincard>>() {}.type


        if(json == null){
            historyList = ArrayList()
        }else{
            historyList = gson.fromJson(json, type)
        }
    }


}