package com.bitbytebit.androidkotlinexercise.showcreditscore.domain

import io.reactivex.Maybe
import io.reactivex.Single

/**
 * [CreditScoreRepository] that:
 * - requests the score from a remote (network) source
 * - caches the value in local storage for offline access
 * - caches the value in memory storage for faster access
 */
class TwoLevelCacheCreditScoreRepository(
        private val networkDataSource: CreditScoreDataSource,
        private val localStorageDataSource: CreditScoreDataSource,
        private val memoryDataSource: CreditScoreDataSource) : CreditScoreRepository {

    override fun getCreditScore(): Single<CreditScore> {
        return getCreditScoreFromMemory()
                .switchIfEmpty(fetchCreditScoreFromNetwork())
                .onErrorResumeNext { _: Throwable? -> loadCreditScoreFromLocalStorage() }
                .toSingle()
    }

    private fun getCreditScoreFromMemory(): Maybe<CreditScore> {
        return memoryDataSource.getCreditScore()
    }

    private fun fetchCreditScoreFromNetwork(): Maybe<CreditScore> {
        return networkDataSource.getCreditScore()
                .flatMap {cacheInDataSource(it, localStorageDataSource) }
                .flatMap {cacheInDataSource(it, memoryDataSource) }
    }

    private fun loadCreditScoreFromLocalStorage(): Maybe<CreditScore> {
        return localStorageDataSource.getCreditScore()
              .flatMap { it -> cacheInDataSource(it, memoryDataSource) }
    }

    private fun cacheInDataSource(score: CreditScore, dataSource: CreditScoreDataSource): Maybe<CreditScore> {
        return dataSource.setCreditScore(score)
                .andThen(Maybe.just(score))
    }

}
