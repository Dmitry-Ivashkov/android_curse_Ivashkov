package com.example.android_curse.ui.onbording

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.R
import com.example.android_curse.databinding.FragmentOnbordingBinding
import com.example.android_curse.ui.base.BaseFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OnbordingFragment : BaseFragment(R.layout.fragment_onbording) {

    private val viewModel: OnbordingViewModel by viewModels()

    private val viewBinding by viewBinding(FragmentOnbordingBinding::bind)

    private fun player(): ExoPlayer = viewBinding.playerView.player as ExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.volumeControlButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.signUpButton.applyInsetter {
            type(navigationBars = true) { margin() }
        }

        viewBinding.playerView.player = SimpleExoPlayer.Builder(requireContext()).build().apply {
            addMediaItem(MediaItem.fromUri("asset:///VID_20190831_213015.mp4"))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
        }

        viewBinding.viewPager.setTextPages()
        viewBinding.viewPager.attachDots(viewBinding.onboardingTextTabLayout)

        viewBinding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_onbordingFragment_to_signInFragment)
        }
        viewBinding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_onbordingFragment_to_signUpFragment)
        }
        viewBinding.volumeControlButton.setOnClickListener { viewModel.change() }
        lifecycleScope.launch {
            viewModel.isOffVolumeControlStateFlow().collect { value: Boolean ->
                if (value) {
                    player().volume = 0f
                    viewBinding.volumeControlButton.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_volume_off_white_24dp,
                            requireContext().theme
                        )
                    )
                } else {
                    player().volume = 100f
                    viewBinding.volumeControlButton.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_volume_on_white_24dp,
                            requireContext().theme
                        )
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.playerView.player?.play()
    }

    override fun onPause() {
        super.onPause()
        viewBinding.playerView.player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.playerView.player?.release()
    }


    private fun ViewPager2.setTextPages() {
        adapter =
            ListDelegationAdapter(onboardingTextAdapterDelegate()).apply {
                items =
                    listOf(
                        getString(R.string.onboarding_view_pager_text_1),
                        getString(R.string.onboarding_view_pager_text_2),
                        getString(R.string.onboarding_view_pager_text_3)
                    )
            }
    }

    private fun ViewPager2.attachDots(tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, this) { _, _ -> }.attach()
    }
}