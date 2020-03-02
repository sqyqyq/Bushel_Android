package com.example.bushelandroid.models

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_row.view.*

class DetailHolder (itemView:View):RecyclerView.ViewHolder(itemView){
    val title = itemView.event_title_text
    val time = itemView.event_time
    val describe = itemView.event_describe
    val location = itemView.location
    val image:ImageView = itemView.event_imageView
}