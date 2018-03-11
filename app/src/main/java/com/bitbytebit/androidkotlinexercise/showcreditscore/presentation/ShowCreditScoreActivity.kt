package com.bitbytebit.androidkotlinexercise.showcreditscore.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.DecelerateInterpolator
import com.bitbytebit.androidkotlinexercise.R
import com.bitbytebit.androidkotlinexercise.from
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.ShowCreditScoreActivityModule
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScore
import kotlinx.android.synthetic.main.activity_show_credit_score.*
import javax.inject.Inject




class ShowCreditScoreActivity : AppCompatActivity(), ShowCreditScorePresenter.View {

    companion object {
        private const val ANIMATION_DURATION_MS = 2000L
    }

    @Inject
    lateinit var presenter : ShowCreditScorePresenter

    private var isRecreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isRecreated = savedInstanceState != null
        setContentView(R.layout.activity_show_credit_score)

        application.from(this)
                .appComponent.plus(ShowCreditScoreActivityModule(this))
                .inject(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.startPresenting()
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun showCreditScore(creditScore: CreditScore) {
        Log.i("ShowCreditScore", "gotten $creditScore")

        creditScoreView.min = creditScore.min
        creditScoreView.max = creditScore.max

        if (!isRecreated) {
            val animation = ObjectAnimator.ofInt(creditScoreView, "score", 0, creditScore.score)
            animation.duration = ANIMATION_DURATION_MS
            animation.interpolator = DecelerateInterpolator()
            animation.start()
        } else {
            creditScoreView.score = creditScore.score
        }

    }


    override fun showGetCreditScoreError(error: Throwable) {
        Log.e("ShowCreditScore", "error", error)
    }
}
