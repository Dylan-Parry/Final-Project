package com.example.finalproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.checkincard.view.*

class historyAdapter(private val historyList: List<checkincard>) : RecyclerView.Adapter<historyAdapter.ExampleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.checkincard, parent, false)
        
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = historyList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.date
        holder.textView2.text = currentItem.notes
        if(currentItem.imageResource == R.drawable.ic_very_happy){
            holder.relativeView.setBackgroundColor(Color.parseColor("#006400"))
        }else if(currentItem.imageResource == R.drawable.ic_happy){
            holder.relativeView.setBackgroundColor(Color.parseColor("#00FF00"))
        }else if(currentItem.imageResource == R.drawable.ic_sad){
            holder.relativeView.setBackgroundColor(Color.parseColor("#FFA500"))
        }else if(currentItem.imageResource == R.drawable.ic_very_sad){
            holder.relativeView.setBackgroundColor(Color.parseColor("#8b0000"))
        }
    }

    override fun getItemCount() = historyList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.imageView
        val textView1: TextView = itemView.text1
        val textView2: TextView = itemView.text2
        val relativeView: RelativeLayout = itemView.relativeView
    }

}