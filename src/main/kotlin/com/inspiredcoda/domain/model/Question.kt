package com.inspiredcoda.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Question(
    @BsonId
    @SerialName("id") var id: String? = null,
    @SerialName("question") val question: String,
    @SerialName("options") val options: List<String>,
    @SerialName("answer") val answer: String,
    @SerialName("difficulty") val difficulty: Difficulty?
)

enum class Difficulty {
    BEGINNER, INTERMEDIATE, ADVANCED
}

