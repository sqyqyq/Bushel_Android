package com.example.bushelandroid.api

import com.example.bushelandroid.models.Event
import com.example.bushelandroid.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun userlogin(
        @Field("Username") Username:String,
        @Field("Password") Password:String
    ):Call<LoginResponse>

    @GET("events")
    fun fetchEvents(@Header("Authorization" )token:String?):Call<List<Event>>
}