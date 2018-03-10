package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

import io.reactivex.Single

interface CreditScoreDataSource {
    fun getCreditScore() : Single<CreditScore>
}
