package com.example.android_curse.ui.news


//import androidx.transition.Transition

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import com.example.android_curse.R
import com.example.android_curse.util.dpToPx
import com.example.android_curse.util.getCompatColor
import com.example.android_curse.util.inflate
import kotlinx.coroutines.*


class PostLikesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleRes) {

    private var textView: TextView

    private var colorRadius: Float = dpToPx(10f)

    var urls: List<String> = listOf()

    private var visibleColorCount: Int = 0
    private var collapsedColorCount: Int = 0

    private var circlePaint: Paint =
        Paint().apply {
            strokeWidth = colorRadius
        }
    private var circleBorderPaint: Paint =
        Paint()
            .apply {
                color = context.getCompatColor(R.color.color_post_like_border)
                strokeWidth = dpToPx(2f)
                style = Paint.Style.STROKE
            }

    init {
        setWillNotDraw(false)
        textView = (inflate(R.layout.item_post_remaining_likes_count, false) as TextView)
            .also { textView ->
                addView(
                    textView,
                    LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT,
                        Gravity.END or Gravity.CENTER_VERTICAL
                    )
                )
            }
//
            setCollapsedColorCountText(urls.size)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setCollapsedColorCountText(urls.size)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (urls.isNotEmpty()) {
            val widthOfOneColor = (colorRadius * 3).toInt()
            val widthOfAllColors = urls.size * widthOfOneColor
            val widthWithoutPadding = measuredWidth - paddingStart - paddingEnd
            var availableSpace = widthWithoutPadding
            if (availableSpace in 1..widthOfAllColors) {
                var visibleColorCount = availableSpace / widthOfOneColor + 1
                do {
                    visibleColorCount--
                    setCollapsedColorCountText(urls.size - visibleColorCount)
                    measureChild(textView, widthMeasureSpec, heightMeasureSpec)
                    availableSpace = widthWithoutPadding - textView.measuredWidth
                } while (0 < visibleColorCount && availableSpace <= visibleColorCount * widthOfOneColor)
                textView.isInvisible = false
                this.visibleColorCount = visibleColorCount
                collapsedColorCount = urls.size - visibleColorCount
            } else {
                textView.isInvisible = true
                visibleColorCount = urls.size
                collapsedColorCount = 0
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val yPos = height / 2f
        var xPos = paddingStart + colorRadius
        for (i in 0 until visibleColorCount) {
            canvas.drawCircle(xPos, yPos, colorRadius, circleBorderPaint)
            MainScope().launch(Dispatchers.IO){
                    val theBitmap: Bitmap = Glide.with(this@PostLikesView)
                        .asBitmap()
                        .load(urls[i])
//                        .load(R.drawable.ic_baseline_person_24)
//                        .load("https://i.pinimg.com/originals/c6/e7/91/c6e7913a6ee055acf1ce60084968f40c.jpg")
                        .circleCrop()
                        .submit((colorRadius*2).toInt(), (colorRadius*2).toInt())
                        .get()
                    canvas.drawBitmap(theBitmap, xPos-colorRadius, yPos-colorRadius, null)
            }
            Thread.sleep(200)  // если не подождать canvas умерает
            xPos += (colorRadius * 3)
        }
    }

    private fun setCollapsedColorCountText(count: Int) {
        textView.text = resources.getString(R.string.common_counter_prefix, count)
    }
}