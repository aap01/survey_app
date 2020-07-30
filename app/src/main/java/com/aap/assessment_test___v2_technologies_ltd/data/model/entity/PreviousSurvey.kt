package com.aap.assessment_test___v2_technologies_ltd.data.model.entity

import com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey.PreviousSurveyItemAdapter

data class PreviousSurvey(
    val id: Long = 0,
    var isExpanded: Boolean = false,
    val dateLong: Long,
    val questions: List<PreviousSurveyQuestion> = emptyList(),
    val adapter: PreviousSurveyItemAdapter = PreviousSurveyItemAdapter()
) {
    val totalQuestions: Int = questions.size
    init {
        adapter.setItemList(questions)
    }
}