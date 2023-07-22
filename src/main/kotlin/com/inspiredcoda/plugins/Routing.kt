package com.inspiredcoda.plugins

import com.inspiredcoda.domain.db.DatabaseBuilder
import com.inspiredcoda.domain.repository.QuizQuestionRepositoryImpl
import com.inspiredcoda.route.quizRoute
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    val database = DatabaseBuilder.createDatabaseInstance()
    routing {
        val quizRepository = QuizQuestionRepositoryImpl(database)
        quizRoute(quizRepository)

        get("/") {
            call.respondText("Hello World!")
        }


    }


}
