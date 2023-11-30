package com.inspiredcoda.route

import com.inspiredcoda.domain.model.BaseResponse
import com.inspiredcoda.domain.model.Instructor
import com.inspiredcoda.domain.repository.InstructorRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * All routes related to the Instructors API
 * */
fun Routing.instructorRoutes(repository: InstructorRepository) {

    get("/instructors") {
        try {
            val responseBody = repository.getInstructors()

            call.respond(
                HttpStatusCode.OK,
                BaseResponse(
                    status = true,
                    responseMessage = if (responseBody.isEmpty()) "No instructors found" else "Instructors retrieved successfully",
                    responseBody = responseBody
                )
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            call.respond(
                HttpStatusCode.OK,
                BaseResponse(
                    status = true,
                    responseMessage = ex.message ?: "Unable to retrieve instructors",
                    responseBody = null
                )
            )
        }
    }

    post("/instructor") {
        try {
            val instructor = call.receive<Instructor>()
            val responseStatus = repository.saveInstructor(instructor)

            call.respond(
                HttpStatusCode.OK,
                BaseResponse(
                    status = responseStatus,
                    responseMessage = if (responseStatus) "Success" else "Failed",
                    responseBody = null
                )
            )

        } catch (ex: Exception) {
            ex.printStackTrace()
            call.respond(
                HttpStatusCode.OK,
                BaseResponse(
                    status = false,
                    responseMessage = ex.message ?: "Failed to add instructor",
                    responseBody = null
                )
            )
        }
    }

    delete("instructor") {

    }

}