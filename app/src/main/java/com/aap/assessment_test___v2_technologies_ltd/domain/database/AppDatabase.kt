package com.aap.assessment_test___v2_technologies_ltd.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.QuestionDDTO
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyDDTO

@Database(
    entities = [QuestionDDTO::class, SurveyDDTO::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun surveyDao(): SurveyDao
}