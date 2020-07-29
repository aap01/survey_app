package com.aap.assessment_test___v2_technologies_ltd.data.mapper

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.QuestionType
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Transformer
import java.util.*

class SurveyToSurveyQuestionD : Transformer<Survey, SurveyQuestionD> {
    override fun map(input: Survey): SurveyQuestionD {
        return SurveyQuestionD(
            surveyD = SurveyD(
              dateLong = Date().time
            ),
            questionsD = input.questions.map { q ->
                QuestionD(
                    question = q.question,
                    options = q.options.joinToString { it.option },
                    required = q.isRequired,
                    type = when (q.type) {
                        QuestionType.MULTI_CHOICE -> QuestionD.MULTIPLE_CHOICE
                        QuestionType.NUMBER -> QuestionD.NUMBER
                        QuestionType.CHECKBOX -> QuestionD.CHECKBOX
                        QuestionType.TEXT -> QuestionD.TEXT
                        QuestionType.DROP_DOWN -> QuestionD.DROPDOWN
                        else -> throw IllegalStateException("QuestionType not defined")
                    },
                    selectedOptions = q.options.filter { it.isSelected }.map { q.options.indexOf(it) }.joinToString { it.toString() },
                    answerFromKeyboard = q.answerFromKeyboard
                )
            }
        )
    }
}