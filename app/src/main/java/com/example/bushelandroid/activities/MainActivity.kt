package com.example.bushelandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bushelandroid.R
import com.example.bushelandroid.api.RetrofitClient
import com.example.bushelandroid.models.AppPreferences
import com.example.bushelandroid.models.LoginResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        login.setOnClickListener {

            val username = edit_username.text.toString().trim()
            val password = edit_password.text.toString().trim()

            if (username.isEmpty()){
            edit_username.error = "Username Required"
            edit_username.requestFocus()
            return@setOnClickListener
            }
            if (password.isEmpty()){
                edit_password.error = "Password Required"
                edit_password.requestFocus()
                return@setOnClickListener
            }


            //retrofit for login
            RetrofitClient.instance.userlogin(username,password).enqueue(object : retrofit2.Callback<LoginResponse>
            {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
                ) {
                    if (!response.body()?.token.isNullOrEmpty()){
                        AppPreferences.getInstance(applicationContext).saveToken(response.body()?.token!!)
                        val intent = Intent(applicationContext, EventActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    }
                }

            })
        }
    }


    //check if isloggedin
    override fun onStart() {
        super.onStart()
        if (AppPreferences.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, EventActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
