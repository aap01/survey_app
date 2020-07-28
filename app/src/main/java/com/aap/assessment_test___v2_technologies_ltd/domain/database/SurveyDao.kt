package com.aap.assessment_test___v2_technologies_ltd.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionDDTO
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyDDTO
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionDDTO

@Dao
interface SurveyDao {

    @Insert
    suspend fun insert(surveyDDTO: SurveyDDTO): Long

    @Transaction
    @Insert
    suspend fun insert(surveyDDTO: SurveyDDTO, questionDDTOs: List<QuestionDDTO>) {
        val surveyId = insert(surveyDDTO)
        questionDDTOs.map {
            it.surveyId = surveyId
        }
        insert(questionDDTOs)
    }

    @Insert
    suspend fun insert(questionDDTOs: List<QuestionDDTO>): List<Long>

    @Transaction
    @Query("SELECT * FROM ${SurveyDDTO.SURVEY_TABLE}")
    suspend fun getPreviousSurveys(): List<SurveyQuestionDDTO>
}