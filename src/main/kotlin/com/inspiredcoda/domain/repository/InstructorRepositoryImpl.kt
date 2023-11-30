package com.inspiredcoda.domain.repository

import com.inspiredcoda.domain.model.Instructor
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*

class InstructorRepositoryImpl(
    private val database: MongoDatabase
): InstructorRepository {

    private val instructorCollection = database.getCollection<Instructor>()

    override suspend fun saveInstructor(instructor: Instructor): Boolean {
        val insertResult = instructorCollection.insertOne(instructor)
        return insertResult.wasAcknowledged()
    }

    override suspend fun getInstructors(): List<Instructor> {
        return instructorCollection.find().toList()
    }

    override suspend fun getInstructor(id: String): Instructor? {
        return instructorCollection.find(Instructor::id eq id).first()
    }

    override suspend fun updateInstructor(id: Long, instructor: Instructor): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInstructor(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}