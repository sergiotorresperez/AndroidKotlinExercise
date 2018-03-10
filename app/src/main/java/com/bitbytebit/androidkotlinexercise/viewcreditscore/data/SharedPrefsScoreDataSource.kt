package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScoreDataSource
import io.reactivex.Completable
import io.reactivex.Maybe


class SharedPrefsScoreDataSource(private val sharedPrefs : SharedPreferences) : CreditScoreDataSource {

    companion object {
        internal const val KEY_CREDIT_SCORE_SCORE = "score"
        internal const val KEY_CREDIT_SCORE_MIN = "min"
        internal const val KEY_CREDIT_SCORE_MAX = "max"
    }

    override fun setCreditScore(score: CreditScore): Completable {
        return Completable.create({
            writeCreditScore(score)
            it.onComplete()
        })
    }

    override fun getCreditScore(): Maybe<CreditScore> {
        with(readCreditScore()) {
            return if (this == null) {
                Maybe.empty()
            } else {
                Maybe.just(this)
            }
        }
    }

    private fun readCreditScore() : CreditScore ? {
        if (!sharedPrefs.contains(KEY_CREDIT_SCORE_SCORE)) {
            return null
        }

        return CreditScore(
                sharedPrefs.getInt(KEY_CREDIT_SCORE_SCORE, 0),
                sharedPrefs.getInt(KEY_CREDIT_SCORE_MIN, 0),
                sharedPrefs.getInt(KEY_CREDIT_SCORE_MAX, 0)
        )
    }

    @SuppressLint("CommitPrefEdits")
    private fun writeCreditScore(score: CreditScore) {
        with(sharedPrefs.edit()) {
            putInt(KEY_CREDIT_SCORE_SCORE, score.score)
            putInt(KEY_CREDIT_SCORE_MIN, score.min)
            putInt(KEY_CREDIT_SCORE_MAX, score.max)
            apply()
        }
    }

}
