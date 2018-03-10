package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever


class CreditScoreResponseMapperTest {

    private lateinit var sut : CreditScoreGetResponseMapper

    @Mock
    private lateinit var creditScoreResponse : CreditScoreResponse

    @Mock
    private lateinit var creditReportInfo: CreditReportInfo


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = CreditScoreGetResponseMapper()

        whenever(creditScoreResponse.creditReportInfo).thenReturn(creditReportInfo)
        whenever(creditReportInfo.score).thenReturn(667)
    }

    @Test(expected = IllegalArgumentException::class)
    fun errorsWhenNullCreditReportInfo() {
        whenever(creditScoreResponse.creditReportInfo).thenReturn(null)
        sut.toCreditScore(creditScoreResponse)
    }

    @Test(expected = IllegalArgumentException::class)
    fun errorsWhenNullMinScore() {
        whenever(creditReportInfo.minScoreValue).thenReturn(null)
        sut.toCreditScore(creditScoreResponse)
    }

    @Test
    fun mapsScore() {
        val expected = 666
        whenever(creditReportInfo.score).thenReturn(expected)

        val actual = sut.toCreditScore(creditScoreResponse).score

        assertEquals("Score should be correctly mapped", actual, expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun errorsWhenNullMaxScore() {
        whenever(creditReportInfo.minScoreValue).thenReturn(null)
        sut.toCreditScore(creditScoreResponse)
    }

    @Test
    fun mapsMinScore() {
        val expected = 0
        whenever(creditReportInfo.minScoreValue).thenReturn(expected)

        val actual = sut.toCreditScore(creditScoreResponse).min

        assertEquals("Minimum should be correctly mapped", actual, expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun errorsWhenNullScore() {
        whenever(creditReportInfo.score).thenReturn(null)
        sut.toCreditScore(creditScoreResponse)
    }

    @Test
    fun mapsMaxScore() {
        val expected = 700
        whenever(creditReportInfo.maxScoreValue).thenReturn(expected)

        val actual = sut.toCreditScore(creditScoreResponse).max

        assertEquals("Maximum should be correctly mapped", actual, expected)
    }

}