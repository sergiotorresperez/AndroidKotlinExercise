package com.bitbytebit.androidkotlinexercise.showcreditscore.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bitbytebit.androidkotlinexercise.R
import com.bitbytebit.androidkotlinexercise.from
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.ShowCreditScoreActivityModule
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScore
import javax.inject.Inject


class ShowCreditScoreActivity : AppCompatActivity(), ShowCreditScorePresenter.View {

    @Inject
    lateinit var presenter : ShowCreditScorePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    override fun showGetCreditScoreError(error: Throwable) {
        Log.e("ShowCreditScore", "error", error)
    }
}
