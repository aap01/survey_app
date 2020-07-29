package com.aap.assessment_test___v2_technologies_ltd.data.usecase

import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.repository.SurveyRepository
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.FetchSurveyUC

class FetchSurveyUCImpl(private val surveyRepository: SurveyRepository) : FetchSurveyUC {
    override suspend fun fetch() = surveyRepository.fetchNewSurvey()
}