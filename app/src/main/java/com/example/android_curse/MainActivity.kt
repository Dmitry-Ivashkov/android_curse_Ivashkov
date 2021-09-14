package com.example.android_curse

import android.database.DataSetObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            ContextCompat.getDrawable(this@MainActivity, R.drawable.item_divider)?.let { setDrawable(it) }
        })


        val adapter = UserAdapter()
        recyclerView.adapter=adapter
        adapter.userList = loadUsers()
        adapter.notifyDataSetChanged()


    }

    fun loadUsers(): List<User> {

        return List(1000) { i -> i }.map { i ->
            User(
                avatarUrl = "",
                userName = "user $i",
                groupName = "A"
            )
        }
    }
}