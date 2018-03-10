package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import retrofit2.Call
import retrofit2.http.GET

/**
 * Declaration of credit scores HTTP API for Retrofit
 */
interface CreditScoreService {

    // TODO: the environment (prod) should not be part of the path and should be configured somewhere else
    @GET("/prod/mockcredit/values")
    fun getCreditScore() : Call<CreditScoreGetResponse>
}