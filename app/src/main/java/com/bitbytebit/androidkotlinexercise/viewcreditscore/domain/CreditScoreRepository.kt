package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

import io.reactivex.Single

/**
 * Repository used to get [CreditScore] from.
 * Its implementation may delegate into different [CreditScoreDataSource]
 */
interface CreditScoreRepository {

    fun getCreditScore() : Single<CreditScore>

}