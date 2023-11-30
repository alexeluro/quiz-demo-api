package com.inspiredcoda.route

import com.inspiredcoda.domain.model.*
import com.inspiredcoda.domain.repository.QuizComputationsRepository
import com.inspiredcoda.domain.repository.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * All routes related to the Quiz API
 * */
fun Route.quizRoute(repository: QuizQuestionRepository, computationRepository: QuizComputationsRepository) {

    val allQuestions = mutableListOf<Question>()

    get("/questions") {
        return@get try {
            val difficulty = call.parameters["difficulty"] ?: "BEGINNER"
            val questions = repository.getQuestions(Difficulty.valueOf(difficulty)).removeAnswers()

            call.respond(
                status = HttpStatusCode.OK,
                BaseResponse<List<Question>>(
                    status = true,
                    responseMessage = "Success",
                    responseBody = questions
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
            } else {
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

    post("compute/questions") {
        try {
            val questionAnswers = call.receive<List<QuizAnswer>>()
            val scoreResponse = computationRepository.calculateScores(questionAnswers)

            call.respond(
                HttpStatusCode.OK,
                BaseResponse(
                    status = true,
                    responseMessage = "Scores calculated successfully",
                    responseBody = QuizAnswerResponse(
                        score = scoreResponse.score,
                        total = scoreResponse.total,
                        comment = scoreResponse.comment
                    )
                )
            )

        } catch (ex: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                BaseResponse(
                    status = true,
                    responseMessage = "Score computation failed. \n${ex.message}",
                    responseBody = null
                )
            )
        }
    }

}