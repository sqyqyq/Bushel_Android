package com.example.bushelandroid.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val base_URL = "https://challenge.myriadapps.com/api/v1/"

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder().baseUrl(base_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
        retrofit.create(Api::class.java)
    }


}

