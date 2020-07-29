package com.aap.assessment_test___v2_technologies_ltd.domain.api

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.network.SurveyNet
import retrofit2.http.GET

interface SurveyApiService {
    @GET("getSurvey")
    fun fetchSurvey(): SurveyNet
}