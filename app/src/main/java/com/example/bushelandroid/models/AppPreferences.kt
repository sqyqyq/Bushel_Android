package com.example.bushelandroid.models

import android.content.Context

class AppPreferences private constructor(private val myContx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = myContx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

//    val token: String?
//        get() {
//            val sharedPreferences = myContx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//            return sharedPreferences.getString("authorization",null)
////                sharedPreferences.getString("username", null),
//
//
//        }


    fun saveToken(token: String?) {

        val sharedPreferences = myContx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

//        editor.putString("username", user.Username)
        editor.putInt("id", 1)
        editor.putString("authorization", token)
        editor.apply()

    }

    fun clear() {
        val sharedPreferences = myContx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: AppPreferences? = null
        @Synchronized
        fun getInstance(myContx: Context): AppPreferences {
            if (mInstance == null) {
                mInstance = AppPreferences(myContx)
            }
            return mInstance as AppPreferences
        }
    }

}