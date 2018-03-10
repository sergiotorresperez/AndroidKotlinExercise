package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

import io.reactivex.Single

class GetCreditScoreInteractor(private val creditScoreRepository : CreditScoreRepository) {

    fun getCreditScore() : Single<CreditScore> = creditScoreRepository.getCreditScore()
}