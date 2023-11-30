package com.inspiredcoda.domain.repository

import com.inspiredcoda.domain.model.QuizAnswer
import com.inspiredcoda.domain.model.QuizAnswerResponse

interface QuizComputationsRepository {

    suspend fun calculateScores(answers: List<QuizAnswer>): QuizAnswerResponse

}