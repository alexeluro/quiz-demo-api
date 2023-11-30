package com.inspiredcoda.domain.repository

import com.inspiredcoda.domain.model.QuizAnswer
import com.inspiredcoda.domain.model.QuizAnswerResponse

class QuizComputationImplRepository(
    private val quizQuestionRepository: QuizQuestionRepository
) : QuizComputationsRepository {

    override suspend fun calculateScores(answers: List<QuizAnswer>): QuizAnswerResponse {
        var score = 0
        answers.map {
            val question = quizQuestionRepository.getQuestion(it.questionId)
            if (question?.answer == it.answer) {
                score++
            }
        }

        val comment = if (score <= answers.size/2) {
            "You can do better"
        } else if (score == answers.size) {
            "Outstanding performance"
        } else {
            "Hmmm... Nice"
        }

        return QuizAnswerResponse(
            score = "${score}",
            total = "${answers.size}",
            comment = comment
        )
    }

}