package com.inspiredcoda.domain.repository

import com.inspiredcoda.domain.model.Difficulty
import com.inspiredcoda.domain.model.Question

interface QuizQuestionRepository {

    suspend fun getQuestions(difficulty: Difficulty): List<Question>
    suspend fun addQuestion(question: Question): Boolean
    suspend fun addQuestions(questions: List<Question>): Boolean
    suspend fun deleteQuestion(id: String): Boolean
    suspend fun updateQuestion(question: Question)

}