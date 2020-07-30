package com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionD.Companion.QUESTION_TABLE

@Entity(
    tableName = QUESTION_TABLE,
    foreignKeys = [ForeignKey(
        entity = SurveyD::class,
        parentColumns = arrayOf(SurveyD.SURVEY_ID_COL),
        childColumns = arrayOf("survey_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class QuestionD(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = QUESTION_ID_COL)
    val id: Long = 0,
    @ColumnInfo(name = "options")
    val options: String,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "required")
    val required: Boolean,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "selected_options_indexes") //Comma separated ints in a string : "2, 4"
    val selectedOptions: String,
    @ColumnInfo(name = "answer_from_keyboard")
    val answerFromKeyboard: String,
    @ColumnInfo(name = "survey_id")
    var surveyId: Long = 0
) {
    companion object {
        const val QUESTION_TABLE = "question"
        const val QUESTION_ID_COL = "q_id"

        const val MULTIPLE_CHOICE = "multiple choice"
        const val TEXT = "text"
        const val DROPDOWN = "dropdown"
        const val NUMBER = "number"
        const val CHECKBOX = "Checkbox"
    }

}