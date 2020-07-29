package com.aap.assessment_test___v2_technologies_ltd.data.di

import DB_NAME
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.aap.assessment_test___v2_technologies_ltd.data.api.BASE_URL
import com.aap.assessment_test___v2_technologies_ltd.data.mapper.SurveyNetToSurvey
import com.aap.assessment_test___v2_technologies_ltd.data.mapper.SurveyQuestionDToSurvey
import com.aap.assessment_test___v2_technologies_ltd.data.mapper.SurveyToSurveyQuestionD
import com.aap.assessment_test___v2_technologies_ltd.data.repository.SurveyRepositoryImpl
import com.aap.assessment_test___v2_technologies_ltd.data.usecase.FetchSurveyUCImpl
import com.aap.assessment_test___v2_technologies_ltd.data.usecase.GetPreviousSurveyUCImpl
import com.aap.assessment_test___v2_technologies_ltd.data.usecase.StoreSurveyUCImpl
import com.aap.assessment_test___v2_technologies_ltd.domain.api.SurveyApiService
import com.aap.assessment_test___v2_technologies_ltd.domain.database.AppDatabase
import com.aap.assessment_test___v2_technologies_ltd.domain.repository.SurveyRepository
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.FetchSurveyUC
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.GetPreviousSurveyUC
import com.aap.assessment_test___v2_technologies_ltd.domain.usecase.StoreSurveyUC
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.CheckboxAdapter
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.FragmentAdapter
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.MCQAdapter
import com.aap.assessment_test___v2_technologies_ltd.presentation.new_survey.NewSurveyVM
import com.aap.assessment_test___v2_technologies_ltd.presentation.previous_survey.PreviousSurveyVM
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Job
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dbModule = module {
    single { Room.databaseBuilder(androidContext().applicationContext, AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().surveyDao() }
}

val netModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    single { get<Retrofit>().create(SurveyApiService::class.java) }
}

val mapperModule = module {
    factory { SurveyNetToSurvey() }
    factory { SurveyToSurveyQuestionD() }
    factory { SurveyQuestionDToSurvey() }
}

val repoModule = module {
    single {
        SurveyRepositoryImpl(
            surveyApiService = get(),
            surveyDao = get(),
            surveyNetToSurvey = get(),
            surveyToSurveyQuestionD = get(),
            surveyQuestionDToSurvey = get()
        ) as SurveyRepository
    }
}

val useCaseModule = module {
    factory {
        FetchSurveyUCImpl(surveyRepository = get()) as FetchSurveyUC
    }
    factory {
        StoreSurveyUCImpl(surveyRepository = get()) as StoreSurveyUC
    }
    factory {
        GetPreviousSurveyUCImpl(surveyRepository = get()) as GetPreviousSurveyUC
    }
}

val viewModelModule = module {
    viewModel {
        NewSurveyVM(
            fetchSurveyUC = get(),
            storeSurveyUC = get(),
            coroutineContext = Job()
        )
    }
    viewModel {
        PreviousSurveyVM(
            getPreviousSurveyUC = get(),
            coroutineContext = Job()
        )
    }
}

val adapterModule = module {
    factory {
        MCQAdapter()
    }
    factory {
        CheckboxAdapter()
    }
}
