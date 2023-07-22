package com.inspiredcoda.domain.repository

import com.inspiredcoda.domain.model.Difficulty
import com.inspiredcoda.domain.model.Question
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection
import org.litote.kmongo.updateOne

class QuizQuestionRepositoryImpl(
    val database: MongoDatabase
) : QuizQuestionRepository {

    val questionCollection = database.getCollection<Question>()

    override suspend fun getQuestions(difficulty: Difficulty): List<Question> {
        return questionCollection.find(Question::difficulty eq difficulty).toList()
    }

    override suspend fun addQuestion(question: Question): Boolean {
        val result = questionCollection.insertOne(question)
        return result.wasAcknowledged()
    }

    override suspend fun addQuestions(questions: List<Question>): Boolean {
        return questionCollection.insertMany(questions).wasAcknowledged()
    }

    override suspend fun deleteQuestion(id: String): Boolean {
        return questionCollection.deleteOne(Question::id eq id).wasAcknowledged()
    }

    override suspend fun updateQuestion(question: Question) {
        questionCollection.updateOne(Question::id eq question.id, question)
    }
}