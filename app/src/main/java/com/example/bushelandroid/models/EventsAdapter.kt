package com.example.bushelandroid.models

import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bushelandroid.R
import com.example.bushelandroid.activities.EventActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.event_row.view.*

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class EventsAdapter (private val events: List<Event>, var clickListner:OnEventClickListner): RecyclerView.Adapter<EventsAdapter.ViewHolder>(){





     class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val eventName :TextView = itemView.eventName
        val eventDate:TextView = itemView.eventDate
        val eventPic:ImageView = itemView.picture
//        val eventRow:ConstraintLayout = itemView.event_row

         fun initialize(item:Event, action:OnEventClickListner){
             eventName.text = item.title
             eventDate.text = item.start_date_time
             Picasso.get().load(item.image_url).transform(RoundedCornersTransformation(20,0)).fit().centerCrop().into(eventPic)

             itemView.setOnClickListener { action.onItemClick(item, adapterPosition) }
         }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_row,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val event = events[position]
//        d("siqing","title?${event.start_date_time}")
//        holder.eventName.text = event.title
////        val startdate = LocalDateTime.parse(event.startDate, DateTimeFormatter.ISO_DATE_TIME)
////        val enddate = LocalDateTime.parse(event.endData, DateTimeFormatter.ISO_DATE_TIME)
////        holder.eventDate.text = startdate.toString()+" - "+enddate.toLocalTime().toString()
//        holder.eventDate.text = event.start_date_time
//        Picasso.get().load(event.image_url).transform(RoundedCornersTransformation(20,0)).fit().centerCrop().into(holder.eventPic)

        holder.initialize(events.get(position), clickListner)

    }

    interface OnEventClickListner{
        fun onItemClick(item:Event,position:Int)
    }

}