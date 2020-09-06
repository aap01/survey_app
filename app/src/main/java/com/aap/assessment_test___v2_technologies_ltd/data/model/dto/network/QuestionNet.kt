package com.aap.assessment_test___v2_technologies_ltd.data.model.dto.network


import com.google.gson.annotations.SerializedName

data class QuestionNet(
    @SerializedName("options")
    val options: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("required")
    val required: Boolean,
    @SerializedName("type")
    val type: String
) {
    companion object {
        const val MULTIPLE_CHOICE = "multiple choice"
        const val TEXT = "text"
        const val DROPDOWN = "dropdown"
        const val NUMBER = "number"
        const val CHECKBOX = "checkbox"
    }
}