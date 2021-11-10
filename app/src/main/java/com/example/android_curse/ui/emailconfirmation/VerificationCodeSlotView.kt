package com.example.android_curse.ui.emailconfirmation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_curse.databinding.ViewVerificationCodeSlotViewBinding

class VerificationCodeSlotView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    val viewBinding =
        ViewVerificationCodeSlotViewBinding.inflate(LayoutInflater.from(context), this)
}