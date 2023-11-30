package com.inspiredcoda.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizAnswer(
    @SerialName("question_id") val questionId: String,
    @SerialName("question_answer") val answer: String
)
