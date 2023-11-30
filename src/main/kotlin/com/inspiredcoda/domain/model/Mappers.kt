package com.inspiredcoda.domain.model

suspend fun List<Question>.removeAnswers(): List<Question> {
    return map {
        it.copy(answer = "")
    }
}