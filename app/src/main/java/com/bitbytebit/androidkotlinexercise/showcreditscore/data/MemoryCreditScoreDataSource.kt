package com.bitbytebit.androidkotlinexercise.showcreditscore.data

import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScoreDataSource
import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * Implementation of [CreditScoreDataSource] based on memory.
 * Useful to implement a memory cache
 */
class MemoryCreditScoreDataSource : CreditScoreDataSource {

    private var score : CreditScore? = null

    override fun setCreditScore(score: CreditScore): Completable {
        return Completable.create({
            this.score = score
            it.onComplete()
        })
    }

    override fun getCreditScore(): Maybe<CreditScore> {
        return if (score == null) {
            Maybe.empty()
        } else {
            Maybe.just(score)
        }
    }
}