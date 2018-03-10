package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import com.bitbytebit.androidkotlinexercise.viewcreditscore.domain.CreditScore

/**
 * Maps the implementation specific [CreditScoreResponse] to the platform agnostic domain object
 * [CreditScore]
 */
class CreditScoreGetResponseMapper {

    fun toCreditScore(response : CreditScoreResponse): CreditScore {

        if (response.creditReportInfo == null) {
            throw IllegalArgumentException("Invalid credit score response: null creditReportInfo")
        }

        with(response.creditReportInfo) {
            if (score == null) {
                throw IllegalArgumentException("Invalid credit score response: null score")
            }
            if (minScoreValue == null) {
                throw IllegalArgumentException("Invalid credit score response: null min score")
            }
            if (maxScoreValue == null) {
                throw IllegalArgumentException("Invalid credit score response: null max score")
            }
            return CreditScore(score, minScoreValue, maxScoreValue)
        }
    }
}