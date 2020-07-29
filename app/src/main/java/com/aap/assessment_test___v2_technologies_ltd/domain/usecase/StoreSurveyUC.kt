package com.aap.assessment_test___v2_technologies_ltd.domain.usecase

import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey

interface StoreSurveyUC {
    suspend fun store(survey: Survey)
}