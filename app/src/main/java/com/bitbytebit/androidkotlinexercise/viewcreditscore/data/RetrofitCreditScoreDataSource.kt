package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScoreDataSource
import io.reactivex.Maybe

class RetrofitCreditScoreDataSource(
        private val service : CreditScoreService,
        private val mapper: CreditScoreGetResponseMapper = CreditScoreGetResponseMapper()) : CreditScoreDataSource {


    override fun getCreditScore(): Maybe<CreditScore> {
        return service.getCreditScore()
                .map { mapper.toCreditScore(it) }
                .flatMapMaybe { Maybe.just(it) }
    }

}
