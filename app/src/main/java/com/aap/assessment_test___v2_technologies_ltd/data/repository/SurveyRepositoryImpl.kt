package com.aap.assessment_test___v2_technologies_ltd.data.repository

import com.aap.assessment_test___v2_technologies_ltd.data.mapper.SurveyNetToSurvey
import com.aap.assessment_test___v2_technologies_ltd.data.mapper.SurveyQuestionDToSurvey
import com.aap.assessment_test___v2_technologies_ltd.data.mapper.SurveyToSurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.api.SurveyApiService
import com.aap.assessment_test___v2_technologies_ltd.domain.database.SurveyDao
import com.aap.assessment_test___v2_technologies_ltd.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SurveyRepositoryImpl(
    private val surveyApiService: SurveyApiService,
    private val surveyDao: SurveyDao,
    private val surveyNetToSurvey: SurveyNetToSurvey,
    private val surveyToSurveyQuestionD: SurveyToSurveyQuestionD,
    private val surveyQuestionDToSurvey: SurveyQuestionDToSurvey
) : SurveyRepository {
    override suspend fun fetchNewSurvey(): Survey {
        return withContext(Dispatchers.IO) {
            surveyNetToSurvey.map(surveyApiService.fetchSurvey().await())
        }
    }

    override suspend fun storeSurvey(survey: Survey) {
        withContext(Dispatchers.IO) {
            surveyDao.insert(surveyToSurveyQuestionD.map(survey))
        }
    }

    override suspend fun getPreviousSurveys(): List<Survey> {
        return withContext(Dispatchers.IO) {
            surveyQuestionDToSurvey.map(surveyDao.getPreviousSurveys())
        }
    }
}