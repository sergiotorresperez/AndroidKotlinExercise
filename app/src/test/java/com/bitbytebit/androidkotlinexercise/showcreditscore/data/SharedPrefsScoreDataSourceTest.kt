package com.bitbytebit.androidkotlinexercise.showcreditscore.data

import android.content.SharedPreferences
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScore
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScoreDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class SharedPrefsScoreDataSourceTest {

    private lateinit var sut : CreditScoreDataSource

    @Mock
    private lateinit var sharedPrefs : SharedPreferences

    @Mock
    private lateinit var editor : SharedPreferences.Editor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = SharedPrefsScoreDataSource(sharedPrefs)

        whenever(sharedPrefs.edit()).thenReturn(editor)
    }

    @Test
    fun getsNullCreditScoreIfNull() {
        whenever(sharedPrefs.contains(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_SCORE)).thenReturn(false)

        sut.getCreditScore().test()
                .assertComplete()
                .assertNoValues()
    }

    @Test
    fun getsCreditScoreIfNotNull() {
        val score = CreditScore(666, 0, 700)

        whenever(sharedPrefs.contains(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_SCORE))).thenReturn(true)
        whenever(sharedPrefs.getInt(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_SCORE), anyInt())).thenReturn(score.score)
        whenever(sharedPrefs.getInt(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_MIN), anyInt())).thenReturn(score.min)
        whenever(sharedPrefs.getInt(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_MAX), anyInt())).thenReturn(score.max)

        sut.getCreditScore().test()
                .assertComplete()
                .assertValue { it == score }
    }

    @Test
    fun setsCreditScore() {
        val score = CreditScore(666, 0, 700)

        sut.setCreditScore(score).test()
                .assertComplete()

        verify(editor).putInt(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_SCORE), eq(score.score))
        verify(editor).putInt(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_MIN), eq(score.min))
        verify(editor).putInt(eq(SharedPrefsScoreDataSource.KEY_CREDIT_SCORE_MAX), eq(score.max))
        verify(editor).apply()
    }
}