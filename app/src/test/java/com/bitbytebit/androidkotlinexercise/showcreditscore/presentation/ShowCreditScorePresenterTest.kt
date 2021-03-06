package com.bitbytebit.androidkotlinexercise.showcreditscore.presentation

import com.bitbytebit.androidkotlinexercise.common.presentation.TestSchedulersProvider
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.GetCreditScoreInteractor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever


class ShowCreditScorePresenterTest {

    private lateinit var sut : ShowCreditScorePresenter

    @Mock
    private lateinit var view : ShowCreditScorePresenter.View

    @Mock
    private lateinit var getCreditScoreInteractor : GetCreditScoreInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = ShowCreditScorePresenter(view, TestSchedulersProvider(), getCreditScoreInteractor)
    }

    @Test
    fun showsGettingCreditSocre() {
        whenever(getCreditScoreInteractor.getCreditScore()).thenReturn(Single.never())

        sut.startPresenting()

        verify(view).showGettingCreditScore()
    }

    @Test
    fun showsCreditScore() {
        val score = Mockito.mock(CreditScore::class.java)
        whenever(getCreditScoreInteractor.getCreditScore()).thenReturn(Single.just(score))

        sut.startPresenting()

        verify(view).showCreditScore(eq(score))
    }

    @Test
    fun showsGetCreditScoreError() {
        val exception = Exception("ouch!")
        whenever(getCreditScoreInteractor.getCreditScore()).thenReturn(Single.error(exception))

        sut.startPresenting()

        verify(view).showGetCreditScoreError(eq(exception))
    }
}