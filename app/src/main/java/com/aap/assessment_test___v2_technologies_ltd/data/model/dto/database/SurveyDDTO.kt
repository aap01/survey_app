package com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyDDTO.Companion.SURVEY_TABLE

@Entity(tableName = SURVEY_TABLE)
data class SurveyDDTO (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SURVEY_ID_COL)
    val id: Long
) {
    companion object {
        const val SURVEY_TABLE = "survey"
        const val SURVEY_ID_COL = "id"
    }
}