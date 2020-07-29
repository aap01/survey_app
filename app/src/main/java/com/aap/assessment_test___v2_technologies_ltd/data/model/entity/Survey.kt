package com.aap.assessment_test___v2_technologies_ltd.data.model.entity

import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Question

data class Survey (
    val id: Long = 0,
    val questions: List<Question>,
    var dateLong: Long = 0
)
