package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import android.support.test.runner.AndroidJUnit4
import com.bitbytebit.androidkotlinexercise.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@RunWith(AndroidJUnit4::class)
class CreditScoreServiceInstrumentedTest {

    private lateinit var sut : CreditScoreService

    @Before
    fun setup() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.CREDIT_SCORE_API_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        sut = retrofit.create(CreditScoreService::class.java)
    }

    @Test
    fun getsCreditScore() {
        sut.getCreditScore().test()
                .assertComplete()
                .assertValue({
                    val actualMin = it.creditReportInfo?.minScoreValue ?: throw AssertionError("MinScore should not be null")
                    val actualMax = it.creditReportInfo?.maxScoreValue ?: throw AssertionError("MaxScore should not be null")
                    val actualScore = it.creditReportInfo?.score ?: throw AssertionError("Score should not be null")
                    actualScore in actualMin..actualMax
                })
    }

}
