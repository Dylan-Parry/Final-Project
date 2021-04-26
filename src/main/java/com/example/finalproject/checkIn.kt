package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class checkIn : AppCompatActivity() {
    var historyList = ArrayList<checkincard>()
    var moodSelection = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        val buttonSave: Button = findViewById(R.id.submit)
        buttonSave.setOnClickListener{
            loadData()
            val drawable = when (moodSelection) {
                1 -> R.drawable.ic_very_happy
                2 -> R.drawable.ic_happy
                3 -> R.drawable.ic_sad
                4 -> R.drawable.ic_very_sad
                else -> {
                    R.drawable.ic_android
                }
            }

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMM hh:mm")
            val formatted = current.format(formatter)

            val notes: EditText = findViewById(R.id.notes)

            if(drawable != R.drawable.ic_android){
                val item = checkincard(drawable, formatted.toString(), notes.text.toString())
                historyList.plusAssign(item)
                saveData()
                checkDay()
                var resultIntent = Intent()
                var extras = Bundle()
                extras.putString("notes", notes.text.toString())
                resultIntent.putExtras(extras)
                setResult(RESULT_OK, resultIntent)
                finish()
            }else{
                Snackbar.make(
                    it,
                    "Please select a mood before submitting.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun checkDay(){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMM")
        val formatted = current.format(formatter).toString()
        val sharedPreferences = getSharedPreferences("day", MODE_PRIVATE)
        val last = sharedPreferences.getString("last day", null )
        if(formatted != last){
            saveDay()
            updateUses()
        }
    }

    private fun updateUses(){
        val sharedPreferences = getSharedPreferences("uses", MODE_PRIVATE)
        var numberOfUses = sharedPreferences.getInt("num uses", 0 )
        numberOfUses += 1
        val edit = sharedPreferences.edit()
        edit.putInt("num uses", numberOfUses)
        edit.apply()
    }

    private fun saveDay(){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMM")
        val formatted = current.format(formatter).toString()
        val sharedPreferences = getSharedPreferences("day", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("last day", formatted)
        edit.apply()
    }


   private fun saveData(){
       val sharedPreferences = getSharedPreferences("history", MODE_PRIVATE)
       val edit = sharedPreferences.edit()
       val gson = Gson()
       val json = gson.toJson(historyList)
       edit.putString("task list", json)
       edit.apply()
    }

    private fun loadData(){
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

    fun veryHappy(view: View){
        moodSelection = 1
    }

    fun happy(view: View){
        moodSelection = 2
    }

    fun sad(view: View){
        moodSelection = 3
    }

    fun verySad(view: View){
        moodSelection = 4
    }
}