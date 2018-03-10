package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScoreDataSource
import io.reactivex.Single

class RetrofitCreditScoreDataSource(
        private val service : CreditScoreService,
        private val mapper: CreditScoreGetResponseMapper = CreditScoreGetResponseMapper()) : CreditScoreDataSource {


    override fun getCreditScore(): Single<CreditScore> = service.getCreditScore().map { mapper.toCreditScore(it) }

}
