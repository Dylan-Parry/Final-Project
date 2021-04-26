package com.example.finalproject


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Stats : AppCompatActivity() {

    var historyList = ArrayList<checkincard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        loadData()
        loadStats()
    }

    @SuppressLint("SetTextI18n")
    private fun loadStats(){
        var veryHappyCount = 0
        var happyCount = 0
        var sadCount = 0
        var verySadCount = 0
        for(item in historyList){
            when(item.imageResource){
                R.drawable.ic_very_happy -> veryHappyCount += 1
                R.drawable.ic_happy -> happyCount += 1
                R.drawable.ic_sad -> sadCount += 1
                R.drawable.ic_very_sad -> verySadCount += 1
            }
        }
        var total = veryHappyCount + happyCount + sadCount + verySadCount

        val vHappyTextView = findViewById<TextView>(R.id.veryHappyCountText)
        val happyTextView = findViewById<TextView>(R.id.happyCountText)
        val sadTextView = findViewById<TextView>(R.id.sadCountText)
        val vSadTextView = findViewById<TextView>(R.id.verySadCountText)
        val totalTextView = findViewById<TextView>(R.id.totalCountText)

        vHappyTextView.text = "Very Happy Count: " + veryHappyCount
        happyTextView.text = "Happy Count: " + happyCount
        vSadTextView.text = "Very Sad Count: " + verySadCount
        sadTextView.text = "Sad Count: " + sadCount
        totalTextView.text = "Total Entries: " + total
    }


    private fun loadData(){
        val sharedPreferences = getSharedPreferences("history", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", null)
        val type = object : TypeToken<ArrayList<checkincard>>() {}.type

        if(json == null){
            historyList = ArrayList()
        }else{
            historyList = gson.fromJson(json, type)
        }
    }


}