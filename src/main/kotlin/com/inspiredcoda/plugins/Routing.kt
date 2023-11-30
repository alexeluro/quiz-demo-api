package com.inspiredcoda.plugins

import com.inspiredcoda.domain.db.DatabaseBuilder
import com.inspiredcoda.domain.repository.InstructorRepositoryImpl
import com.inspiredcoda.domain.repository.QuizComputationImplRepository
import com.inspiredcoda.domain.repository.QuizQuestionRepositoryImpl
import com.inspiredcoda.route.instructorRoutes
import com.inspiredcoda.route.quizRoute
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val database = DatabaseBuilder.createDatabaseInstance()
    routing {
        val quizRepository = QuizQuestionRepositoryImpl(database)
        val computationRepository = QuizComputationImplRepository(quizRepository)
        quizRoute(quizRepository, computationRepository)

        val instructorRepository = InstructorRepositoryImpl(database)
        instructorRoutes(instructorRepository)

        get("/") {
            call.respondText("Hello World!")
        }


    }


}
