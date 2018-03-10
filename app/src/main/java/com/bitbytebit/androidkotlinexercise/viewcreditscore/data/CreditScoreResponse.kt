package com.bitbytebit.androidkotlinexercise.viewcreditscore.data

import com.squareup.moshi.Json

/**
 * Data classes to parse the response of the HTTP request into.
 * These were automatically generated using RoboPOJOGenerator
 * REF: https://github.com/robohorse/RoboPOJOGenerator
 */
data class CreditScoreResponse(

	@Json(name="dashboardStatus")
	val dashboardStatus: String? = null,

	@Json(name="personaType")
	val personaType: String? = null,

	@Json(name="coachingSummary")
	val coachingSummary: CoachingSummary? = null,

	@Json(name="augmentedCreditScore")
	val augmentedCreditScore: Any? = null,

	@Json(name="creditReportInfo")
	val creditReportInfo: CreditReportInfo? = null,

	@Json(name="accountIDVStatus")
	val accountIDVStatus: String? = null
)

data class CoachingSummary(

	@Json(name="activeChat")
	val activeChat: Boolean? = null,

	@Json(name="numberOfTodoItems")
	val numberOfTodoItems: Int? = null,

	@Json(name="activeTodo")
	val activeTodo: Boolean? = null,

	@Json(name="numberOfCompletedTodoItems")
	val numberOfCompletedTodoItems: Int? = null,

	@Json(name="selected")
	val selected: Boolean? = null
)

data class CreditReportInfo(

	@Json(name="numPositiveScoreFactors")
	val numPositiveScoreFactors: Int? = null,

	@Json(name="changeInShortTermDebt")
	val changeInShortTermDebt: Int? = null,

	@Json(name="clientRef")
	val clientRef: String? = null,

	@Json(name="currentShortTermDebt")
	val currentShortTermDebt: Int? = null,

	@Json(name="percentageCreditUsedDirectionFlag")
	val percentageCreditUsedDirectionFlag: Int? = null,

	@Json(name="score")
	val score: Int? = null,

	@Json(name="currentShortTermNonPromotionalDebt")
	val currentShortTermNonPromotionalDebt: Int? = null,

	@Json(name="currentLongTermDebt")
	val currentLongTermDebt: Int? = null,

	@Json(name="changedScore")
	val changedScore: Int? = null,

	@Json(name="currentShortTermCreditLimit")
	val currentShortTermCreditLimit: Int? = null,

	@Json(name="percentageCreditUsed")
	val percentageCreditUsed: Int? = null,

	@Json(name="daysUntilNextReport")
	val daysUntilNextReport: Int? = null,

	@Json(name="currentLongTermCreditLimit")
	val currentLongTermCreditLimit: Any? = null,

	@Json(name="currentLongTermCreditUtilisation")
	val currentLongTermCreditUtilisation: Any? = null,

	@Json(name="equifaxScoreBand")
	val equifaxScoreBand: Int? = null,

	@Json(name="minScoreValue")
	val minScoreValue: Int? = null,

	@Json(name="currentShortTermCreditUtilisation")
	val currentShortTermCreditUtilisation: Int? = null,

	@Json(name="changeInLongTermDebt")
	val changeInLongTermDebt: Int? = null,

	@Json(name="equifaxScoreBandDescription")
	val equifaxScoreBandDescription: String? = null,

	@Json(name="monthsSinceLastDelinquent")
	val monthsSinceLastDelinquent: Int? = null,

	@Json(name="numNegativeScoreFactors")
	val numNegativeScoreFactors: Int? = null,

	@Json(name="hasEverBeenDelinquent")
	val hasEverBeenDelinquent: Boolean? = null,

	@Json(name="currentLongTermNonPromotionalDebt")
	val currentLongTermNonPromotionalDebt: Int? = null,

	@Json(name="scoreBand")
	val scoreBand: Int? = null,

	@Json(name="hasEverDefaulted")
	val hasEverDefaulted: Boolean? = null,

	@Json(name="maxScoreValue")
	val maxScoreValue: Int? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="monthsSinceLastDefaulted")
	val monthsSinceLastDefaulted: Int? = null
)