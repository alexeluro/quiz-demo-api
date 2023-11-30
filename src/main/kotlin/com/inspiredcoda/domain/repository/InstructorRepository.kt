package com.inspiredcoda.domain.repository

import com.inspiredcoda.domain.model.Instructor

interface InstructorRepository {

    suspend fun saveInstructor(instructor: Instructor): Boolean
    suspend fun getInstructors(): List<Instructor>

    suspend fun getInstructor(id: String): Instructor?

    suspend fun updateInstructor(id: Long, instructor: Instructor): Boolean

    suspend fun deleteInstructor(id: Long): Boolean

}