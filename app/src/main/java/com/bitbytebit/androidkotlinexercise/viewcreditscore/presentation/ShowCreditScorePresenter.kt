package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation

import android.util.Log
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.GetCreditScoreInteractor
import io.reactivex.disposables.CompositeDisposable

/*
    Design notes about this presenter:

    - To make the presenter fully independent from the Android, I have avoided reflecting the
      Android lifecycle here (#onStart(), #onStop(), etc)
      I have declared the methods startPresenting() and stopPresenting(), which are platform
      independent.
      I have seen developers that tie the presenter to the lifecycle of the Activity - that is
      reasonable, but I prefer not to do it

    - I've seen developers declaring the methods of the presenter in an interface.
      As the presenter depends on abstractions I think that is not strictly necessary and I declare
      interfaces only for the view. Interfaces for the presenter would be also acceptable to me.

    - I've seen developers setting the Schedulers of the RX operations in the interactors.
      I prefer to do so in the presenter so that interactors can be chained more easily, but I'm
      fine either way.
 */
class ShowCreditScorePresenter(
        private val view : View,
        private val getCreditScoreInteractor : GetCreditScoreInteractor) {

    companion object {
        private val LOG_TAG = ShowCreditScorePresenter::class.java.simpleName
    }

    private val compositeDisposable = CompositeDisposable()

    fun startPresenting() {
        getCreditScore()
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    private fun getCreditScore() {
        val subscription = getCreditScoreInteractor.getCreditScore()
                .subscribe({ processGetCreditScoreSuccess(it) },
                           { processGetCreditScoreError(it) })

        compositeDisposable.add(subscription)
    }

    private fun processGetCreditScoreSuccess(creditScore: CreditScore) {
        view.showCreditScore(creditScore)
    }

    private fun processGetCreditScoreError(error: Throwable) {
        Log.e(LOG_TAG, "Error getting creditScore", error)
        view.showGetCreditScoreError(error)
    }

    interface View {
        fun showCreditScore(creditScore: CreditScore)
        fun showGetCreditScoreError(error: Throwable)
    }
}
