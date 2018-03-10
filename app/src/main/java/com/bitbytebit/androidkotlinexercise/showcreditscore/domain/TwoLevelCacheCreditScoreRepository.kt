package com.bitbytebit.androidkotlinexercise.showcreditscore.domain

import io.reactivex.Maybe
import io.reactivex.MaybeSource
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
                .compose({ cacheInDataSource(localStorageDataSource, it) })
                .compose({ cacheInDataSource(memoryDataSource, it) })
    }

    private fun loadCreditScoreFromLocalStorage(): Maybe<CreditScore> {
        return localStorageDataSource.getCreditScore()
                .compose({ cacheInDataSource(memoryDataSource, it) })
    }

    private fun cacheInDataSource(dataSource: CreditScoreDataSource, upstream: Maybe<CreditScore>): MaybeSource<CreditScore> {
        return upstream
                .flatMapCompletable { dataSource.setCreditScore(it) }
                .andThen(upstream)
    }

}
