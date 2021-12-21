package com.example.android_curse.util

import android.view.View
import androidx.annotation.Dimension

fun View.dpToPx(@Dimension(unit = Dimension.DP) dp: Float): Float = dpToPx(resources.displayMetrics, dp)