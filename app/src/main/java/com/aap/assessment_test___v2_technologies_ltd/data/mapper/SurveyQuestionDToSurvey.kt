package com.aap.assessment_test___v2_technologies_ltd.data.mapper

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Option
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Question
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.QuestionType
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Transformer
class SurveyQuestionDToSurvey: Transformer<List<SurveyQuestionD>, List<Survey>> {
    override fun map(input: List<SurveyQuestionD>): List<Survey> {
        return input.map {
            Survey(
                id = it.surveyD.id,
                questions = it.questionsD.map { q ->
                    val selectedIndexes = mutableListOf<Int>()
                    selectedIndexes.addAll(q.selectedOptions.split(",").map { it.trim().toInt() })
                    Question(
                        question = q.question,
                        type = when (q.type) {
                            QuestionD.DROPDOWN -> QuestionType.DROP_DOWN
                            QuestionD.MULTIPLE_CHOICE -> QuestionType.MULTI_CHOICE
                            QuestionD.TEXT -> QuestionType.TEXT
                            QuestionD.CHECKBOX -> QuestionType.CHECKBOX
                            QuestionD.NUMBER -> QuestionType.NUMBER
                            else -> throw IllegalStateException("QuestionD.type not configured" + q.type)
                        },
                        options = q.options
                            .split(",")
                            .mapIndexed { index, option ->
                                Option(
                                    option.trim(),
                                    isSelected = q.selectedOptions
                                        .split(",")
                                        .map { it.trim().toInt() }
                                        .contains(index)
                                )
                            },
                        answerFromKeyboard = q.answerFromKeyboard,
                        isRequired = q.required
                    )
                },

                dateLong = it.surveyD.dateLong
            )
        }.sortedByDescending { it.dateLong }
    }

}