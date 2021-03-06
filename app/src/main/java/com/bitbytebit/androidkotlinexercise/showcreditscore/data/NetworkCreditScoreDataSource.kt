package com.bitbytebit.androidkotlinexercise.showcreditscore.data

import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScoreDataSource
import io.reactivex.Completable
import io.reactivex.Maybe
import java.lang.UnsupportedOperationException

class NetworkCreditScoreDataSource(
        private val service : CreditScoreService,
        private val mapper: CreditScoreGetResponseMapper = CreditScoreGetResponseMapper()) : CreditScoreDataSource {

    override fun getCreditScore(): Maybe<CreditScore> {
        return service.getCreditScore()
                .map { mapper.toCreditScore(it) }
                .flatMapMaybe { Maybe.just(it) }
    }

    override fun setCreditScore(score: CreditScore) : Completable {
        return Completable.error(UnsupportedOperationException("Posting credit score is not supported"))
    }
}
