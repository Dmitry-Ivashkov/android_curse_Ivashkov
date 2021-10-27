package com.example.android_curse.ui.profile

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentBookmarksBinding
import com.example.android_curse.ui.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val viewBinding by viewBinding(FragmentBookmarksBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()
}