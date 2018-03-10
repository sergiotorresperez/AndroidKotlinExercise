package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bitbytebit.androidkotlinexercise.R
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore

class ShowCreditScoreActivity : AppCompatActivity(), ShowCreditScorePresenter.View {

    lateinit var presenter : ShowCreditScorePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_credit_score)
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
    }

    override fun showGetCreditScoreError(error: Throwable) {
        Log.e("ShowCreditScore", "error", error)
    }
}
