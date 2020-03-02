package com.example.bushelandroid.api

import com.example.bushelandroid.models.Event
import com.example.bushelandroid.models.EventDetail
import com.example.bushelandroid.models.LoginResponse
import com.example.bushelandroid.models.Speaker
import retrofit2.Call
import retrofit2.Response
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

    @GET("events/{id}")
    fun fetchEventsDetail(@Path("id") id:Int,
                          @Header("Authorization" )token:String?):Call<EventDetail>

//    @GET("speakers/{id}")
//    suspend fun fetchSpeakersDetail(@Path("id") id:Int,
//                          @Header("Authorization" )token:String?):Response<Speaker>
    @GET("speakers/{id}")
    fun fetchSpeakersDetail(@Path("id") id:Int,
                                    @Header("Authorization" )token:String?):Call<Speaker>
}