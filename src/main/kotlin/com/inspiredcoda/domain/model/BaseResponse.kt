package com.inspiredcoda.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("status") val status: Boolean = false,
    @SerialName("responseMessage") val responseMessage: String,
    @SerialName("responseBody") val responseBody: T?
)
