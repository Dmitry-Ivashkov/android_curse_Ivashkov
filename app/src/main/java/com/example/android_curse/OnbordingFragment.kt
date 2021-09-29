package com.example.android_curse

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android_curse.databinding.ActivityMainBinding
import com.example.android_curse.databinding.FragmentOnbordingBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class OnbordingFragment : Fragment(R.layout.fragment_onbording){

    private val viewBinding by viewBinding(FragmentOnbordingBinding::bind)
//    private lateinit var binding: FragmentOnbordingBinding

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = FragmentOnbordingBinding.inflate(layoutInflater)

         //TODO()
//        viewBinding.playerView.player = player!!  //TODO()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player=SimpleExoPlayer.Builder(requireContext()).build().apply {
            addMediaItem(MediaItem.fromUri("asset:///VID_20190615_173334.mp4"))
            repeatMode=Player.REPEAT_MODE_ALL
            prepare()
        }

        viewBinding.playerView.player = player!!

        viewBinding.viewPager.setTextPages()
        viewBinding.viewPager.attachDots(viewBinding.onboardingTextTabLayout)

        viewBinding.signInButton.setOnClickListener {
//            TODO()
            Toast.makeText(requireContext(), "нажата текста войти",Toast.LENGTH_SHORT).show()
        }
        viewBinding.signUpButton.setOnClickListener {
//            TODO()
            Toast.makeText(requireContext(), "нажата текста регистрации",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
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