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
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentListUsersBinding
import com.example.android_curse.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : BaseFragment(R.layout.fragment_list_users) {

    val viewModel: UserListViewModel by viewModels()

    private val viewBinding by viewBinding(FragmentListUsersBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    renderViewState(viewState)
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerView.isVisible = false
        viewBinding.progressBar.isVisible = true
        setupRecyclerView()
    }

    private fun setupRecyclerView(): UserAdapter {
        val recyclerView = viewBinding.recyclerView.apply {
            layoutManager =
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
                viewBinding.recyclerView.isVisible = false
                viewBinding.progressBar.isVisible = true
                viewBinding.errorText.isVisible = false
            }
            is UserListViewModel.ViewState.Data -> {
                viewBinding.recyclerView.isVisible = true
                (viewBinding.recyclerView.adapter as UserAdapter).userList.value = viewState.userList
                viewBinding.progressBar.isVisible = false
                viewBinding.errorText.isVisible = false
            }
            is UserListViewModel.ViewState.EmptyList -> {
                viewBinding.recyclerView.isVisible = false
                viewBinding.progressBar.isVisible = false
                viewBinding.errorText.isVisible = true
                viewBinding.errorText.text="увы у вас пока нет друзей"
            }
            is UserListViewModel.ViewState.Error -> {
                viewBinding.recyclerView.isVisible = false
                viewBinding.progressBar.isVisible = false
                viewBinding.errorText.isVisible = true
                viewBinding.errorText.text="возникла какае-та ошибка"
            }
        }
    }

}