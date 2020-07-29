package com.aap.assessment_test___v2_technologies_ltd.data.mapper

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.network.QuestionNet
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Question
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.QuestionType
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Transformer

class SurveyNetToSurvey : Transformer<List<QuestionNet>, Survey> {
    override fun map(input: List<QuestionNet>): Survey {
        return Survey(
            questions = input.map {
                Question(
                    question = it.question,
                    type = when (it.type) {
                        QuestionNet.DROPDOWN -> QuestionType.DROP_DOWN
                        QuestionNet.MULTIPLE_CHOICE -> QuestionType.MULTI_CHOICE
                        QuestionNet.TEXT -> QuestionType.TEXT
                        QuestionNet.CHECKBOX -> QuestionType.CHECKBOX
                        QuestionNet.NUMBER -> QuestionType.NUMBER
                        else -> throw IllegalStateException("QuestionD.type not configured" + it.type)
                    },
                    options = it.options.split(", ").map { it.trim() },
                    isRequired = it.required
                )
            }
        )
    }
}