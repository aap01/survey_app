package com.aap.assessment_test___v2_technologies_ltd.data.mapper

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Transformer

class SurveyToSurveyQuestionD : Transformer<Survey, SurveyQuestionD> {
    override fun map(input: Survey): SurveyQuestionD {
        TODO("Not yet implemented")
    }
}