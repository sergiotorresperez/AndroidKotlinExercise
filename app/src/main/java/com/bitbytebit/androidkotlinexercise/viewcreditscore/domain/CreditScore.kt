package com.bitbytebit.androidkotlinexercise.viewcreditscore.domain

/**
 * Data class encapsulating one credit score.
 *
 * This class does not depend on platform specific implementation details, such as the JSON format
 * of [com.bitbytebit.androidkotlinexercise.viewcreditscore.data.CreditScoreResponse].
 * This is because in a clean architecture we inverse the dependiencies, so that the business logic
 * relies on abstractions: [https://en.wikipedia.org/wiki/Dependency_inversion_principle]
 */
data class CreditScore(val score : Int, val min : Int, val max : Int)
