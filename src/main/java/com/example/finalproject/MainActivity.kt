package com.example.finalproject

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadUses()

    }


    fun reset(view: View){
        getSharedPreferences("uses", MODE_PRIVATE).edit().clear().apply()
        getSharedPreferences("day", MODE_PRIVATE).edit().clear().apply()
        getSharedPreferences("history", MODE_PRIVATE).edit().clear().apply()
    }

    override fun onResume() {
        super.onResume()
        loadUses()
    }


    private fun loadUses(){
        val sharedPreferences = getSharedPreferences("uses", MODE_PRIVATE)
        val uses = sharedPreferences.getInt("num uses", 1)

        if(uses >= 1){
            var ac1 = findViewById<CardView>(R.id.achievementOne)
            ac1.visibility = View.VISIBLE
        }
        if(uses >= 7){
            var ac2 = findViewById<CardView>(R.id.achievementTwo)
            ac2.visibility = View.VISIBLE
        }
        if(uses >= 31){
            var ac3 = findViewById<CardView>(R.id.achievementThree)
            ac3.visibility = View.VISIBLE
        }
        if(uses >= 365){
            var ac4 = findViewById<CardView>(R.id.achievementFour)
            ac4.visibility = View.VISIBLE
        }
    }

    fun checkin(view: View){
        val intent = Intent(this, checkIn::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == RESULT_OK) {
                var intentExtras = data?.extras
                var notes = intentExtras?.getString("notes")
                if (notes != null) {
                    if(notes.contains("suicide", ignoreCase = true) || notes.contains("kill myself", ignoreCase = true)
                        || notes.contains("cut", ignoreCase = true) || notes.contains("disappear", ignoreCase = true))
                        {
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage("Are you okay? Would you like to talk to a charity about your day?")
                                .setPositiveButton("Yes",
                                    DialogInterface.OnClickListener { _, _ ->
                                        val intent = Intent(this, emergencyContact::class.java)
                                        startActivity(intent)
                                    })
                                .setNegativeButton("No",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        // User cancelled the dialog
                                    })
                            var emergency = builder.create()
                            emergency.show()
                    }

                }
            }
        }
    }

    fun stats(view: View){
        val intent = Intent(this, Stats::class.java)
        startActivity(intent)
    }

    fun emergency(view: View){
        val intent = Intent(this, emergencyContact::class.java)
        startActivity(intent)
    }

    fun history(view: View){
        val intent = Intent(this, history::class.java)
        startActivity(intent)
    }


}
