package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScoreDataSource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class RetrofitCreditScoreDataSourceTest {

    private lateinit var sut : CreditScoreDataSource

    @Mock
    private lateinit var service : CreditScoreService

    @Mock
    private lateinit var mapper: CreditScoreGetResponseMapper

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = RetrofitCreditScoreDataSource(service, mapper)
    }

    @Test
    fun getsCreditScore() {
        val response = mock(CreditScoreResponse::class.java)
        whenever(service.getCreditScore()).thenReturn(Single.just(response))

        val expected = mock(CreditScore::class.java)
        whenever(mapper.toCreditScore(response)).thenReturn(expected)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == expected }
    }

}

