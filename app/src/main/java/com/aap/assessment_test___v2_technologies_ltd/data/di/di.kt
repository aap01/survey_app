package com.aap.assessment_test___v2_technologies_ltd.data.di

import DB_NAME
import androidx.room.Room
import com.aap.assessment_test___v2_technologies_ltd.data.api.BASE_URL
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.database.SurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.model.dto.network.SurveyNet
import com.aap.assessment_test___v2_technologies_ltd.data.model.entity.Survey
import com.aap.assessment_test___v2_technologies_ltd.data.model.mapper.SurveyNetToSurvey
import com.aap.assessment_test___v2_technologies_ltd.data.model.mapper.SurveyToSurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.repository.SurveyRepositoryImpl
import com.aap.assessment_test___v2_technologies_ltd.domain.api.SurveyApiService
import com.aap.assessment_test___v2_technologies_ltd.domain.database.AppDatabase
import com.aap.assessment_test___v2_technologies_ltd.domain.mapper.Mapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dbModule = module {
    single { Room.databaseBuilder(androidContext().applicationContext, AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().surveyDao() }
}

val netModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(SurveyApiService::class.java) }
}

val mapperModule = module {
    single { SurveyNetToSurvey() as Mapper<SurveyNet, Survey> }
    single { SurveyToSurveyQuestionD() as Mapper<Survey, SurveyQuestionD> }
}

val repoModule = module {
    single {
        SurveyRepositoryImpl(
            surveyApiService = get(),
            surveyDao = get(),
            surveyNetToSurvey = get(),
            surveyToSurveyQuestionD = get()
        )
    }
}

val useCaseModule = module {

}

val viewModelModule = module {

}
