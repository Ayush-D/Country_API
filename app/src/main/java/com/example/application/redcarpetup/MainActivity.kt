package com.example.application.redcarpetup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJson()
    }

    fun fetchJson(){
        val url = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call?, response: IOException?) {

                println("Failed to execute")

                Toast.makeText(this@MainActivity, "Error Occured",
                        Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val curiousConcept = gson.fromJson(body,CuriousConcept::class.java)

                runOnUiThread {
                    recyclerView_main.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL,false);
                    //recyclerView_main.layoutManager = LinearLayoutManager(baseContext)
                    recyclerView_main.adapter = MainAdapterKotlin(this@MainActivity,curiousConcept)
                }
            }

        })
    }

    class CuriousConcept(@SerializedName("worldpopulation") val WldPop:List<Worldpopulation>)

    class Worldpopulation(val rank:Int,val country:String , val flag:String, val population:String)
}
