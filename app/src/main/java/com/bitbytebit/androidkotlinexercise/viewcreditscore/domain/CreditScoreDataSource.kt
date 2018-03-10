package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

import io.reactivex.Maybe

interface CreditScoreDataSource {
    fun getCreditScore() : Maybe<CreditScore>
}
