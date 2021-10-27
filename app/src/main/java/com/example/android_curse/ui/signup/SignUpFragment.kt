package com.example.android_curse.ui.signup

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentSignUpBinding
import com.example.android_curse.ui.base.BaseFragment

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {
    private val viewBinding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()
}