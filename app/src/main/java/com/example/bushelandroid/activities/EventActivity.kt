package com.example.bushelandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bushelandroid.R
import com.example.bushelandroid.api.RetrofitClient
import com.example.bushelandroid.models.AppPreferences
import com.example.bushelandroid.models.Event
import com.example.bushelandroid.models.EventsAdapter
import kotlinx.android.synthetic.main.activity_event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventActivity : AppCompatActivity(),EventsAdapter.OnEventClickListner {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        //set toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //toolbar title
        val actionbar = supportActionBar
        actionbar!!.title = "Events"



        //use retrofit get events
        RetrofitClient.instance.fetchEvents(AppPreferences.getInstance(this).getToken()).enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {

                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                d("siqing","onFailure")
            }

        })
    }


    //apply recycle view
    private fun showData(Events: List<Event>) {
        event_recycleView.apply {
            layoutManager  = LinearLayoutManager(this@EventActivity)
            this.addItemDecoration(DividerItemDecoration(this@EventActivity,1))
            adapter = EventsAdapter(Events,this@EventActivity )

        }

    }

    //add menu button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.mian_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //add logout in menu button
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

    //check if logout
    override fun onStart() {
        super.onStart()
        if (!AppPreferences.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }

    //method of click item in recycle view
    override fun onItemClick(item: Event, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
       intent.putExtra("ID", item.id)
       intent.putExtra("TITLE", item.title)

        startActivity(intent)
    }
}
