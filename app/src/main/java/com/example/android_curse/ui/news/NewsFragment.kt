package com.example.android_curse.ui.news

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentNewsBinding
import com.example.android_curse.ui.base.BaseFragment

class NewsFragment : BaseFragment(R.layout.fragment_news) {
    private val viewBinding by viewBinding(FragmentNewsBinding::bind)

    private val viewModel: NewsViewModel by viewModels()
}