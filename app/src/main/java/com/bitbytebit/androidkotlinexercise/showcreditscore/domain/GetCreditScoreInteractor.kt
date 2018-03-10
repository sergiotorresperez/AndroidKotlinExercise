package com.bitbytebit.androidkotlinexercise.showcreditscore.domain

import io.reactivex.Single
import javax.inject.Inject

class GetCreditScoreInteractor @Inject constructor(private val creditScoreRepository : CreditScoreRepository) {

    fun getCreditScore() : Single<CreditScore> = creditScoreRepository.getCreditScore()
}