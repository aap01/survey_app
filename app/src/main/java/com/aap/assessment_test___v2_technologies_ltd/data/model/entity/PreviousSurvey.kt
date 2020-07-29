package com.aap.assessment_test___v2_technologies_ltd.data.model.entity

data class PreviousSurvey(
    val isExpanded: Boolean = false,
    val dateLong: Long,
    val totalQuestions: Int,
    val questions: List<Question> = emptyList()
)