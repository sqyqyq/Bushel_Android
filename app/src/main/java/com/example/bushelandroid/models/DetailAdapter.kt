package com.example.bushelandroid.models

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bushelandroid.R
import com.example.bushelandroid.api.RetrofitClient
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailAdapter ( private val postlist:MutableList<Speaker>, private val context:EventDetail) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val EVENT_DETAIL = 1
    private val EVENT_SPEAKER = 2
//    private val size = context.speakers.size
//    private val speakers = context.speakers
//    val postlist = getlist()


    //create for two viewholders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View
        return if(viewType == EVENT_DETAIL){
            view = LayoutInflater.from(parent.context).inflate(R.layout.detail_row, parent, false)
            DetailHolder(view)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.speaker_row, parent, false)
            SpeakerHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return (postlist.size+1)//add one because EventDetail has one
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        //if it is first then, use DetailHolder otherwise use Speaker Holder
        if (position ==0){

            val data = context

            val viewHolder = holder as DetailHolder

            viewHolder.title.text = data.title
            viewHolder.describe.text = data.event_description
            viewHolder.location.text = data.location
            val startdate = LocalDateTime.parse(data.start_date_time, DateTimeFormatter.ISO_DATE_TIME)
            val enddate = LocalDateTime.parse(data.end_date_time, DateTimeFormatter.ISO_DATE_TIME)
            viewHolder.time.text = startdate.toString()+" - "+enddate.toLocalTime().toString()

            Picasso.get().load(data.image_url).fit().centerCrop().into(viewHolder.image)

        }
        else{
            val viewHolder = holder as SpeakerHolder

            val i = position
//            Log.d("siqing", "cao")
            val data = postlist[i-1]//-1 because need - the Event Detail one

            viewHolder.name.text = data.first_name+" "+data.last_name
            viewHolder.describe.text = data.bio

        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) EVENT_DETAIL else  EVENT_SPEAKER
    }
}