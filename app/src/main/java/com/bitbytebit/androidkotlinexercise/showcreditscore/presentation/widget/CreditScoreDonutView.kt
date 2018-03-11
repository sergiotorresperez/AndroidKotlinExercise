package com.bitbytebit.androidkotlinexercise.showcreditscore.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bitbytebit.androidkotlinexercise.R
import kotlinx.android.synthetic.main.view_credit_score.view.*

class CreditScoreDonutView : FrameLayout {

    var min : Int = 0

    var max : Int
        get() = min + creditScoreProgressBar.max
        set(value) {
            creditScoreProgressBar.max = max - min
            creditScoreMaxTextView.text = String.format(context.getString(R.string.credit_score_view_max), value)
        }

    var score : Int
    get() = creditScoreProgressBar.progress
    set(value) {
        creditScoreProgressBar.progress = value - min
        creditScoreTextView.text = (value - min).toString()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_credit_score, this, true)
    }
}
