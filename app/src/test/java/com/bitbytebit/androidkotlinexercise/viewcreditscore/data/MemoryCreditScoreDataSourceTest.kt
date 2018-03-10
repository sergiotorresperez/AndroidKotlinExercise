package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScoreDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class MemoryCreditScoreDataSourceTest {

    private lateinit var sut : CreditScoreDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = MemoryCreditScoreDataSource()
    }

    @Test
    fun getsNullCreditScoreWhenNotSet() {
        sut.getCreditScore().test()
                .assertComplete()
                .assertNoValues()
    }

    @Test
    fun setsAndGetsCreditScore() {
        val score = mock(CreditScore::class.java)

        sut.setCreditScore(score).test()
                .assertComplete()

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }
    }
}