package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

import com.nhaarman.mockito_kotlin.any
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class TwoLevelCacheCreditScoreRepositoryTest {

    private lateinit var sut : CreditScoreRepository

    @Mock
    private lateinit var networkDataSource : CreditScoreDataSource

    @Mock
    private lateinit var localStorageDataSource : CreditScoreDataSource

    @Mock
    private lateinit var memoryDataSource : CreditScoreDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setupBasicMocking(networkDataSource)
        setupBasicMocking(localStorageDataSource)
        setupBasicMocking(memoryDataSource)

        sut = TwoLevelCacheCreditScoreRepository(networkDataSource, localStorageDataSource, memoryDataSource)
    }

    private fun setupBasicMocking(dataSource: CreditScoreDataSource) {
        whenever(dataSource.getCreditScore()).thenReturn(Maybe.empty())
        whenever(dataSource.setCreditScore(any())).thenReturn(Completable.complete())
    }


    @Test
    fun returnsScoreFromMemoryWhenAvailable() {
        val score = Mockito.mock(CreditScore::class.java)

        givenMemoryCacheReturnsScore(score)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }
    }

    @Test
    fun returnsScoreFromNetworkWhenNotInMemory() {
        val score = Mockito.mock(CreditScore::class.java)

        givenScoreNotCachedInMemory()
        givenNetworkDataSourceReturnsScore(score)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }
    }

    @Test
    fun returnsScoreFromLocalStorageWhenNetworkError() {
        val score = Mockito.mock(CreditScore::class.java)

        givenScoreNotCachedInMemory()
        givenNetworDataSourceFails()
        givenLocalStorageDataSourceReturnsScore(score)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }
    }

    @Test
    fun persistsRemoteScoreInLocalStorage() {
        val score = Mockito.mock(CreditScore::class.java)

        givenScoreNotCachedInMemory()
        givenNetworkDataSourceReturnsScore(score)
        val subscriptionWitness = givenLocalStorageDataSourceSavesScore(score)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }

        subscriptionWitness.assertSubscribed()
    }

    @Test
    fun cachesRemoteScoreInMemory() {
        val score = Mockito.mock(CreditScore::class.java)

        givenScoreNotCachedInMemory()
        givenNetworkDataSourceReturnsScore(score)
        val subscriptionWitness = givenMemoryCacheDataSourceSavesScore(score)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }

        subscriptionWitness.assertSubscribed()
    }

    @Test
    fun cachesLocalScoreInMemory() {
        val score = Mockito.mock(CreditScore::class.java)

        givenScoreNotCachedInMemory()
        givenNetworDataSourceFails()
        givenLocalStorageDataSourceReturnsScore(score)
        val subscriptionWitness = givenMemoryCacheDataSourceSavesScore(score)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }

        subscriptionWitness.assertSubscribed()
    }

    private fun givenScoreNotCachedInMemory() {
        whenever(memoryDataSource.getCreditScore()).thenReturn(Maybe.empty())
    }

    private fun givenMemoryCacheReturnsScore(score: CreditScore) {
        return givenDataSourceReturnsScore(score, memoryDataSource)
    }

    private fun givenNetworkDataSourceReturnsScore(score : CreditScore) {
        return givenDataSourceReturnsScore(score, networkDataSource)
    }

    private fun givenNetworDataSourceFails() {
        whenever(networkDataSource.getCreditScore()).thenReturn(Maybe.error(Exception()))
    }

    private fun givenLocalStorageDataSourceReturnsScore(score: CreditScore) {
        return givenDataSourceReturnsScore(score, localStorageDataSource)
    }

    private fun givenDataSourceReturnsScore(score : CreditScore, dataSource: CreditScoreDataSource) {
        whenever(dataSource.getCreditScore()).thenReturn(Maybe.just(score))
    }

    private fun givenLocalStorageDataSourceSavesScore(score : CreditScore): TestObserver<Unit> {
        return givenDataSourceSavesScore(score, localStorageDataSource)
    }

    private fun givenMemoryCacheDataSourceSavesScore(score : CreditScore): TestObserver<Unit> {
        return givenDataSourceSavesScore(score, memoryDataSource)
    }

    private fun givenDataSourceSavesScore(score : CreditScore, dataSource: CreditScoreDataSource): TestObserver<Unit> {
        val subscriptionWitness = TestObserver.create<Unit>()
        val testCompletable = Completable.complete().doOnSubscribe({ subscriptionWitness.onSubscribe(it) })

        whenever(dataSource.setCreditScore(score)).thenReturn(testCompletable)
        return subscriptionWitness
    }

}