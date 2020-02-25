package com.example.bushelandroid.api

import com.example.bushelandroid.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun userlogin(
        @Field("Username") Username:String,
        @Field("Password") Password:String
    ):Call<LoginResponse>
}