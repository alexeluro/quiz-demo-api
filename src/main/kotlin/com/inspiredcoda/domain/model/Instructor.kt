package com.inspiredcoda.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Instructor(
    @BsonId
    @SerialName("id") val id: String? = null,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("phone") val phone: String,
    @SerialName("email") val email: String,
    @SerialName("rating") val rating: Long = 5,
    @SerialName("description") val description: String
)
