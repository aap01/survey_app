package com.aap.assessment_test___v2_technologies_ltd.data.usecase

import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.repository.SurveyRepository
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.StoreSurveyUC

class StoreSurveyUCImpl(private val surveyRepository: SurveyRepository) : StoreSurveyUC {
    override suspend fun store(survey: Survey) {
        surveyRepository.storeSurvey(survey)
    }
}