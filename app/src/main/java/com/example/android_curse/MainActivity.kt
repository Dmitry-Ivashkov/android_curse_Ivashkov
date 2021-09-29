package com.example.android_curse

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_curse.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
//    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = setupRecyclerView()

        binding.mainViewModel = viewModel

//        adapter.userList =
//            viewModel.viewState.map {
//                when (it) {
//                    is MainViewModel.ViewState.Loading -> {
//                        findViewById<RecyclerView>(R.id.recyclerView).isVisible = false
//                        findViewById<View>(R.id.progressBar).isVisible = true
//                        listOf()
//                    }
//                    is MainViewModel.ViewState.Data -> {
//                        findViewById<RecyclerView>(R.id.recyclerView).isVisible = true
//                        findViewById<View>(R.id.progressBar).isVisible = false
//                        it.userList
//                    }
//                }
//            }.stateIn(lifecycleScope, SharingStarted.Eagerly, listOf())



//        findViewById<RecyclerView>(R.id.recyclerView).isVisible = false
//        findViewById<View>(R.id.progressBar).isVisible = true
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    renderViewState(viewState)
                }
            }
        }
    }

    private fun setupRecyclerView(): UserAdapter {
        val recyclerView = binding.recyclerView.apply {
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

    private fun renderViewState(viewState: MainViewModel.ViewState) {
        when (viewState) {
            is MainViewModel.ViewState.Loading -> {
                binding.recyclerView.isVisible = false
                binding.progressBar.isVisible = true
            }
            is MainViewModel.ViewState.Data -> {
                binding.recyclerView.isVisible = true
                (binding.recyclerView.adapter as UserAdapter).userList.value = viewState.userList
                binding.progressBar.isVisible = false
            }
        }
    }

}