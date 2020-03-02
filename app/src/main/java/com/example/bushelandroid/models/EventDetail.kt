package com.example.bushelandroid.models

class EventDetail (

        val id:Int,
        val title:String,
        val image_url:String,
        val event_description:String,
        val start_date_time:String,
        val end_date_time:String,
        val location:String,
        val featured:Boolean,
        val speakers:Array<SpeakersID>

    )
