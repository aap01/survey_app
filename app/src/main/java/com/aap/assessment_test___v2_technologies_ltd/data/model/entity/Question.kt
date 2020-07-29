package com.aap.assessment_test___v2_technologies_ltd.data.model.entity

import java.io.Serializable

open class Question (
    val question: String,
    val type: QuestionType,
    val options: List<Option> = emptyList(),
    var answerFromKeyboard: String = "",
    val isRequired: Boolean
): Serializable