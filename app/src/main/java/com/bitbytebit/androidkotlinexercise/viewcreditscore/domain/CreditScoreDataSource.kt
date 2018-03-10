package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

import io.reactivex.Completable
import io.reactivex.Maybe

interface CreditScoreDataSource {
    fun getCreditScore() : Maybe<CreditScore>

    fun setCreditScore(score : CreditScore) : Completable
}
