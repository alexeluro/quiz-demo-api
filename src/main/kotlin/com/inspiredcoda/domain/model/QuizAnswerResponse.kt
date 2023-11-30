package com.inspiredcoda.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizAnswerResponse(
    @SerialName("score") val score: String,
    @SerialName("total") val total: String,
    @SerialName("comment") val comment: String
)
