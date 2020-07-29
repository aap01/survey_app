package com.aap.assessment_test___v2_technologies_ltd.data.model.entity

open class Question (
    val question: String,
    val type: QuestionType,
    val options: List<String> = emptyList(),
    val answerIndexes: MutableList<Int>  = mutableListOf(),
    var answerFromKeyboard: String = "",
    val isRequired: Boolean
) {

}