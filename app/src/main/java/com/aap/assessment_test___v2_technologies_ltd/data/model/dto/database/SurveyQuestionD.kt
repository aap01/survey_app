package com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database

import androidx.room.Embedded
import androidx.room.Relation
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey

data class SurveyQuestionD (
    @Embedded
    var surveyD: SurveyD,
    @Relation(
        parentColumn = SurveyD.SURVEY_ID_COL,
        entityColumn = "survey_id"
    ) var questionsD: List<QuestionD>
)