package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

/**
 * Repository used to get [CreditScore] from.
 * Its implementation may delegate into different [CreditScoreDataSource]
 */
interface CreditScoreRepository {
    fun getCreditScore() : CreditScore
}