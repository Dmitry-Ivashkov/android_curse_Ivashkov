package com.example.android_curse.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentSignInBinding
import com.example.android_curse.ui.base.BaseFragment

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewBinding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel: SignInViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backBottom.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.signInButton.setOnClickListener {
            viewModel.signIn()
        }
    }
}