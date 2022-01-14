package com.example.android_curse.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentNewsBinding
import com.example.android_curse.ui.base.BaseFragment

class NewsFragment : BaseFragment(R.layout.fragment_news) {
    private val viewBinding by viewBinding(FragmentNewsBinding::bind)

    private val viewModel: NewsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val url =
            "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png"
        viewBinding.likes.urls = listOf(url).flatMap { listOf(it, it, it, it, it, it) }
            .flatMap { listOf(it, it, it, it, it, it) } // 25
        super.onViewCreated(view, savedInstanceState)
    }
}