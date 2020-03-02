package com.example.bushelandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bushelandroid.R
import com.example.bushelandroid.api.Api
import com.example.bushelandroid.api.RetrofitClient
import com.example.bushelandroid.models.*

import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class DetailActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        //set toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //toolbar title
        val actionbar = supportActionBar
        actionbar!!.title = intent.getStringExtra("TITLE")

        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        //get all speakers
        val All_list = getlist()

        //get event detail with retrofit
        RetrofitClient.instance.fetchEventsDetail(intent.getIntExtra("ID",1),AppPreferences.getInstance(this).getToken()).enqueue(object: Callback<EventDetail> {
            override fun onFailure(call: Call<EventDetail>, t: Throwable) {

            }

            override fun onResponse(call: Call<EventDetail>, response: Response<EventDetail>) {
//                Log.d("siqing", response.body().toString())
//                val list = getlist(response.body()!!.speakers)

                //select speakers from all speakers
                val list = mutableListOf<Speaker>()
                for(item in response.body()!!.speakers){
                    list.add(All_list[item.id-1])
                }
                showData(response.body()!!,list)
            }

        })


    }


    //method of get all speakers
    fun getlist():MutableList<Speaker>{
        val list = mutableListOf<Speaker>()
//        val list = listOf<Speaker>()
        for(item in 1..11 ){

            RetrofitClient.instance.fetchSpeakersDetail(item,AppPreferences.getInstance(this).getToken()).enqueue(object: Callback<Speaker> {
                override fun onFailure(call: Call<Speaker>, t: Throwable) {
//                    Log.d("siqing", "nonono")
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Speaker>, response: Response<Speaker>) {
                    list.add(response.body()!!)
//                    Log.d("siqing", "added")
                }
            })

        }
//        Log.d("siqing", "yews")
        return list
    }


    //apply recycle view
    private fun showData( detail: EventDetail ,list: MutableList<Speaker>) {
        event_recycleView.apply {
            layoutManager  = LinearLayoutManager(this@DetailActivity)
            this.addItemDecoration(DividerItemDecoration(this@DetailActivity,1))
            adapter = DetailAdapter( list,detail)


        }
    }

    //add menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.mian_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //add logout in menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id==R.id.action_logout){
            AppPreferences.getInstance(this).clear()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    //check if logout, if logouted then go to login page
    override fun onStart() {
        super.onStart()
        if (!AppPreferences.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }
}
