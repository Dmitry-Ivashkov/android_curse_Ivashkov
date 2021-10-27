package com.example.android_curse.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_curse.R
import com.example.android_curse.databinding.ActivityMainBinding
import com.example.android_curse.ui.base.BaseFragment
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserListFragment : BaseFragment(R.layout.activity_main) {

    val viewModel: UserListViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
//    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView()


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

        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = true
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    renderViewState(viewState)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivityMainBinding.inflate(inflater)
//        binding.mainViewModel = viewModel


        val adapter = setupRecyclerView(inflater, container)

        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    private fun setupRecyclerView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): UserAdapter {
        val recyclerView = binding.recyclerView.apply {
            layoutManager =
//                inflater.inflate(R.layout.activity_main, container)
                LinearLayoutManager(
                    this@UserListFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )

            addItemDecoration(
                DividerItemDecoration(
                    this@UserListFragment.context,
                    DividerItemDecoration.VERTICAL
                ).apply {
                    ContextCompat.getDrawable(
                        this@UserListFragment.requireContext(),
                        R.drawable.item_divider
                    )
                        ?.let { setDrawable(it) }
                })
        }
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        return adapter
    }

    private fun renderViewState(viewState: UserListViewModel.ViewState) {
        when (viewState) {
            is UserListViewModel.ViewState.Loading -> {
                binding.recyclerView.isVisible = false
                binding.progressBar.isVisible = true
            }
            is UserListViewModel.ViewState.Data -> {
                binding.recyclerView.isVisible = true
                (binding.recyclerView.adapter as UserAdapter).userList.value = viewState.userList
                binding.progressBar.isVisible = false
            }
        }
    }

}