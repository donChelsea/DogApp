package com.katsidzira.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.katsidzira.dogapp.adapter.DogsAdapter
import com.katsidzira.dogapp.model.DogsApi
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val imageList = ArrayList<DogsApi>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEt : EditText
    private lateinit var searchBtn : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        searchEt = findViewById(R.id.edittext_search)
        searchBtn = findViewById(R.id.fab)

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        searchBtn.setOnClickListener {
            var name = searchEt.text.toString()
            searchDogs(name)
        }

    }

    private fun searchDogs(name: String) {
        imageList.clear()
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    for (i in 0 until image.length()) {
                        val list = image.get(i)
                        imageList.add(
                            DogsApi(list.toString())
                        )
                    }
                    recyclerView.adapter = DogsAdapter(this@MainActivity, imageList)
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })
    }
}