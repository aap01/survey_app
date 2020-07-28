package com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database

import androidx.room.Embedded
import androidx.room.Relation
import com.aap.assessment_test___v2_technologies_ltd.data.model.Survey

data class SurveyQuestionDDTO (
    @Embedded
    var survey: Survey,
    @Relation(
        parentColumn = SurveyDDTO.SURVEY_ID_COL,
        entityColumn = QuestionDDTO.QUESTION_ID_COL
    ) val questions: List<QuestionDDTO>
)