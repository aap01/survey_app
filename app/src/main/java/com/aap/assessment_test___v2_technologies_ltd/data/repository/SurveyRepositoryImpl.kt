package com.aap.assessment_test___v2_technologies_ltd.data.repository

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.network.SurveyNet
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.data.model.mapper.SurveyNetToSurvey
import com.aap.assessment_test___v2_technologies_ltd.domain.api.SurveyApiService
import com.aap.assessment_test___v2_technologies_ltd.domain.database.SurveyDao
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Mapper
import com.aap.assessment_test___v2_technologies_ltd.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SurveyRepositoryImpl(
    private val surveyApiService: SurveyApiService,
    private val surveyDao: SurveyDao,
    private val surveyNetToSurvey: Mapper<SurveyNet, Survey>,
    private val surveyToSurveyQuestionD: Mapper<Survey, SurveyQuestionD>
) : SurveyRepository {
    override suspend fun fetchNewSurvey(): Survey {
        return withContext(Dispatchers.IO) {
            surveyNetToSurvey.map(surveyApiService.fetchSurvey())
        }
    }

    override suspend fun storeSurvey(survey: Survey) {
        withContext(Dispatchers.IO) {
            surveyDao.insert(surveyToSurveyQuestionD.map(survey))
        }
    }

    override suspend fun getPreviousSurveys(): List<Survey> {
        TODO("Not yet implemented")
    }
}