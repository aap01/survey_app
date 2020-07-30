package com.aap.assessment_test___v2_technologies_ltd.data.mapper

import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.*
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Transformer
class SurveyQuestionDToPreviousSurvey: Transformer<List<SurveyQuestionD>, List<PreviousSurvey>> {
    override fun map(input: List<SurveyQuestionD>): List<PreviousSurvey> {
        return input.map {
            println("survey id ${it.surveyD.id} total questions ${it.questionsD.size} ")
            val prev = PreviousSurvey(
                id = it.surveyD.id,
                questions = it.questionsD.map { q ->

                    val options = q.options.split(",").map { it.trim() }
                    val selectedIndices = if (q.selectedOptions.isNotBlank()) q.selectedOptions.split(",").map { it.trim().toInt() } else emptyList()
                    val optionsAnswer = selectedIndices.map { options[it] }.joinToString { it }

                    val question =PreviousSurveyQuestion(
                        question = q.question,
                        answer =  if (optionsAnswer.isBlank()) q.answerFromKeyboard else optionsAnswer
                    )
                    if (question.answer.isBlank()) question.answer = "N/A"
                    question
                },
                dateLong = it.surveyD.dateLong
            )
            prev
        }.sortedByDescending { it.dateLong }
    }

}