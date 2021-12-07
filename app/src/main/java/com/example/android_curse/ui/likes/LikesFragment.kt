package com.example.android_curse.ui.likes

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentLikesBinding
import com.example.android_curse.ui.base.BaseFragment

class LikesFragment : BaseFragment(R.layout.fragment_likes) {
    private val viewBinding by viewBinding(FragmentLikesBinding::bind)

    private val viewModel: LikesViewModel by viewModels()
}