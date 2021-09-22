package com.example.android_curse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_curse.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = setupRecyclerView()

        binding.mainViewModel = viewModel

        findViewById<RecyclerView>(R.id.recyclerView).isVisible=false
        findViewById<View>(R.id.progressBar).isVisible=true

//        adapter.userList=

//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                adapter.userList = viewModel.viewState
//                adapter.notifyDataSetChanged()
//                findViewById<RecyclerView>(R.id.recyclerView).isVisible=true
//                findViewById<View>(R.id.progressBar).isVisible=false
//            }
//        }
    }

    private fun setupRecyclerView(): UserAdapter {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                ).apply {
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.item_divider)
                        ?.let { setDrawable(it) }
                })
        }
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        return adapter
    }

}