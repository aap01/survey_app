package com.aap.assessment_test___v2_technologies_ltd.data.model

open class Question (
    val question: String,
    val type: QuestionType,
    val options: List<String> = emptyList(),
    val answers: MutableList<Int>  = mutableListOf(),
    var answerFromKeyboard: String = ""
)