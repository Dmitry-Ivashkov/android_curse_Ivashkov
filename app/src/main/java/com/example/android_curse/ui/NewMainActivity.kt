package com.example.android_curse.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.NewActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewMainActivity : AppCompatActivity(R.layout.new_activity_main) {

    val viewModel: NewMainViewModel by viewModels()

    private val viewBinding by viewBinding(NewActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        subscribeToAuthorizationStatus()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        super.onCreate(savedInstanceState)
    }

    private fun subscribeToAuthorizationStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isAuthorizedFlow().collect {
                    showSuitableNavigationFlow(it)
                }
            }
        }
    }

    private fun showSuitableNavigationFlow(isAuthorized: Boolean) {
        val navController = findNavController(R.id.mainActivityNavigationHost)
        when (isAuthorized) {
            true -> {
                if (navController.backQueue.any { it.destination.id == R.id.user_nav_graph }) {
                    return
                }
                navController.navigate(R.id.action_registeredUserNavGraph)
            }
            false -> {
                if (navController.backQueue.any { it.destination.id == R.id.guest_nav_graph }) {
                    return
                }
                navController.navigate(R.id.action_guestNavGraph)
            }
        }
    }
}