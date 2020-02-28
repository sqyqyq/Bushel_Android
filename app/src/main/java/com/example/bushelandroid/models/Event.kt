package com.example.bushelandroid.models

data class Event (
        val id:Int,
        val title:String,
        val image_url:String,
        val start_date_time:String,
        val end_date_time:String,
        val location:String,
        val featured:Boolean
)