package com.inspiredcoda.route

import com.inspiredcoda.domain.model.BaseResponse
import com.inspiredcoda.domain.model.Difficulty
import com.inspiredcoda.domain.model.Question
import com.inspiredcoda.domain.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.quizRoute(repository: QuizQuestionRepository) {

    val allQuestions = mutableListOf<Question>()

    get("/questions") {
        return@get try {
            val difficulty = call.parameters["difficulty"] ?: "BEGINNER"

            call.respond(
                status = HttpStatusCode.OK,
                BaseResponse<List<Question>>(
                    status = true,
                    responseMessage = "Success",
                    responseBody = repository.getQuestions(Difficulty.valueOf(difficulty))
                )
            )
        } catch (ex: Exception) {
            call.respond(
                status = HttpStatusCode.RequestTimeout,
                BaseResponse<List<Question>>(
                    status = true,
                    responseMessage = "Request timeout. ${ex.message}",
                    responseBody = null
                )
            )
        }

    }

    post("/add/question") {
        return@post try {
            val question = call.receive<Question>()

            if (repository.addQuestion(question)) {
                call.respond(
                    status = HttpStatusCode.OK,
                    BaseResponse<List<Question>>(
                        status = true,
                        responseMessage = "Success",
                        responseBody = emptyList()
                    )
                )
            } else {
                call.respond(
                    status = HttpStatusCode.OK,
                    BaseResponse<List<Question>>(
                        status = false,
                        responseMessage = "Failed to add question",
                        responseBody = emptyList()
                    )
                )
            }

        } catch (ex: Exception) {
            call.respond(
                status = HttpStatusCode.RequestTimeout,
                BaseResponse<List<Question>>(
                    status = false,
                    responseMessage = "Request timeout. \n${ex.message}",
                    responseBody = null
                )
            )
        }
    }

    post("/add/question-list") {
        return@post try {
            val question = call.receive<List<Question>>()

            if (repository.addQuestions(question)) {
                call.respond(
                    status = HttpStatusCode.OK,
                    BaseResponse<List<Question>>(
                        status = true,
                        responseMessage = "Question added successfully",
                        responseBody = emptyList()
                    )
                )
            }else{
                call.respond(
                    status = HttpStatusCode.OK,
                    BaseResponse<List<Question>>(
                        status = false,
                        responseMessage = "Failed to add questions",
                        responseBody = emptyList()
                    )
                )
            }

        } catch (ex: Exception) {
            call.respond(
                status = HttpStatusCode.RequestTimeout,
                BaseResponse<List<Question>>(
                    status = false,
                    responseMessage = "Request timeout. \n${ex.message}",
                    responseBody = null
                )
            )
        }
    }

}