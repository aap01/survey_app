package com.aap.assessment_test___v2_technologies_ltd.data.util

sealed class ModelResponse<T>

class SuccessResponse<T>(val body: T) : ModelResponse<T>()

class ErrorResponse<T>(val errorCode: Int = 0, val errorMessage: String? = "Unknown Error") :
    ModelResponse<T>()

class Loading<T> : ModelResponse<T>()
