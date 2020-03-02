package com.example.bushelandroid.models

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.speaker_row.view.*

class SpeakerHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    val name = itemView.speaker_name
    val describe = itemView.speaker_describe
}