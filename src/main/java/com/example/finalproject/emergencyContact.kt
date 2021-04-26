package com.example.finalproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class emergencyContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_contact)
    }




    fun samCall(view: View){
        var number = "tel:116 123"
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(number)))
    }

    fun samWeb(view: View){
        var web = "https://www.samaritans.org/?nation=wales"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(web)))
    }

    fun mind(view: View){
        var web = "https://www.mind.org.uk/"
        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(web)))
    }

    fun shout(view: View){
        var web = "https://giveusashout.org/"
        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(web)))
    }

}