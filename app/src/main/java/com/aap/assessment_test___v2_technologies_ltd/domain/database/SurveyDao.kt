package com.aap.assessment_test___v2_technologies_ltd.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD

@Dao
interface SurveyDao {

    @Insert
    suspend fun insert(surveyD: SurveyD): Long

    @Transaction
    @Insert
    suspend fun insert(surveyQuestionD: SurveyQuestionD) {
        val surveyId = insert(surveyQuestionD.surveyD)
        surveyQuestionD.questionsD.map {
            it.surveyId = surveyId
        }
        insert(surveyQuestionD.questionsD).map {
            println("Inserted item $it")
        }
    }

    @Insert
    suspend fun insert(questionsD: List<QuestionD>): List<Long>

    @Transaction
    @Query("SELECT * FROM ${SurveyD.SURVEY_TABLE}")
    suspend fun getPreviousSurveys(): List<SurveyQuestionD>

    @Query("SELECT * FROM ${QuestionD.QUESTION_TABLE}")
    suspend fun getAllQs(): List<QuestionD>

    @Query("SELECT * FROM ${SurveyD.SURVEY_TABLE}")
    suspend fun getAllSs(): List<SurveyD>
}